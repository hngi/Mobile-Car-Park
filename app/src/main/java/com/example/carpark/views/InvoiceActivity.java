package com.example.carpark.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.carpark.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.pdf417.encoder.BarcodeMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class InvoiceActivity extends AppCompatActivity {

    private ImageView qrImage;
    private Object BarcodeMatrix;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        getSupportActionBar().setTitle("Invoice");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initView();
    }

    private void initView() {
        qrImage = findViewById(R.id.qr_code);
        showQrCode();
    }

    public void showQrCode(){


        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        //final Bitmap bm = null;
        String barcodeNumber = BarcodeMatrix.toString();
        try {
           // bm = encod
            BitMatrix bitMatrix = multiFormatWriter.encode(barcodeNumber, BarcodeFormat.UPC_A, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);

            qrImage.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

    }

    public void encodeAsBitmap(){

    }


}
