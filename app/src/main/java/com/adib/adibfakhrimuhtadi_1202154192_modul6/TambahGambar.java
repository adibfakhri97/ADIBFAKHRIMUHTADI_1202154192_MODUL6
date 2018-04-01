package com.adib.adibfakhrimuhtadi_1202154192_modul6;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class TambahGambar extends AppCompatActivity {
    String Storage_Path = "feed/";
    public static final String Database_Path = "ADIB";
    Button pilihGambar;
    EditText ImageTitle;
    ImageView SelectImage;
    Uri FilePathUri;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    int Image_Request_Code = 7;

    ProgressDialog progressDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_gambar);

        storageReference = FirebaseStorage.getInstance().getReference();

        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        pilihGambar = (Button)findViewById(R.id.pilihFoto);

        ImageTitle = (EditText)findViewById(R.id.ImageNameEditText);

        SelectImage = (ImageView)findViewById(R.id.imageViewTambah);

        progressDialog = new ProgressDialog(TambahGambar.this);

        pilihGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Creating intent.
                Intent intent = new Intent();

                // Setting intent type as image to select image from phone storage.
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Please Select Image"), Image_Request_Code);

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabTambah);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadImageFileToFirebaseStorage();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                SelectImage.setImageBitmap(bitmap);
                pilihGambar.setText("Image Selected");

            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    public void UploadImageFileToFirebaseStorage() {

        if (FilePathUri != null) {

            progressDialog.setTitle("Mengunggah gambar...");
            progressDialog.show();
            StorageReference storageReference2nd = storageReference.child(Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));
            storageReference2nd.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String TempImageName = ImageTitle.getText().toString().trim();

                            progressDialog.dismiss();

                            Toast.makeText(getApplicationContext(), "Unggah berhasil", Toast.LENGTH_LONG).show();

                            @SuppressWarnings("VisibleForTests")
                            methodAdapter1 imageUploadInfo = new methodAdapter1(TempImageName, taskSnapshot.getDownloadUrl().toString(), tabLayout.emailUser);

                            String ImageUploadId = databaseReference.push().getKey();
                            databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
                            Intent i = new Intent(TambahGambar.this,tabLayout.class);
                            startActivity(i);
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();

                            Toast.makeText(TambahGambar.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })

                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            progressDialog.setTitle("Mengunggah gambar...");

                        }
                    });

        }
        else {

            Toast.makeText(TambahGambar.this, "Pilih gambar", Toast.LENGTH_LONG).show();
        }
    }


}
