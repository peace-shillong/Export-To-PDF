package com.peace.exporttopdf;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import crl.android.pdfwriter.PDFWriter;
import crl.android.pdfwriter.PaperSize;
import crl.android.pdfwriter.StandardFonts;
import crl.android.pdfwriter.Transformation;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class MainActivity extends AppCompatActivity {

    String pdfcontent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private String generatePdf() {
        PDFWriter writer = new PDFWriter(PaperSize.FOLIO_WIDTH, PaperSize.FOLIO_HEIGHT);
        writer.setFont(StandardFonts.SUBTYPE, StandardFonts.TIMES_ROMAN);


        writer.addRawContent("1 0 0 rg\n");
        writer.addTextAsHex(70, 50, 12, "68656c6c6f20776f726c6420286173206865782921");
        writer.setFont(StandardFonts.SUBTYPE, StandardFonts.COURIER, StandardFonts.WIN_ANSI_ENCODING);
        writer.addRawContent("0 0 0 rg\n");
        writer.addText(30, 90, 10, "� CRL", Transformation.DEGREES_270_ROTATION);

        writer.newPage();
        writer.addRawContent("[] 0 d\n");
        writer.addRawContent("1 w\n");
        writer.addRawContent("0 0 1 RG\n");
        writer.addRawContent("0 1 0 rg\n");
        writer.addRectangle(40, 50, 280, 50);
        writer.addText(85, 75, 18, "Code Research Laboratories");

        writer.newPage();
        writer.setFont(StandardFonts.SUBTYPE, StandardFonts.COURIER_BOLD);
        writer.addText(150, 150, 14, "http://coderesearchlabs.com");
        writer.addLine(150, 140, 270, 140);

        int pageCount = writer.getPageCount();
        for (int i = 0; i < pageCount; i++) {
            writer.setCurrentPage(i);
            writer.addText(10, 10, 8, Integer.toString(i + 1) + " / " + Integer.toString(pageCount));
        }

        String s = writer.asString();
        return s;
    }


    private void outputToFile(String fileName, String pdfContent, String encoding) {
        File downloads = Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS);
        if (!downloads.exists() && !downloads.mkdirs())
            throw new RuntimeException("Could not create download folder");

        File newFile = new File(downloads, fileName);
        Log.i("PDF", "Writing file to " + newFile);

        try {
            newFile.createNewFile();
            try {
                FileOutputStream pdfFile = new FileOutputStream(newFile);
                pdfFile.write(pdfContent.getBytes(encoding));
                pdfFile.close();
            } catch(FileNotFoundException e) {
                Log.w("PDF", e);
            }
        } catch(IOException e) {
            Log.w("PDF", e);
        }
    }

    public void btnClick(View view) {
        pdfcontent=generatePdf();
        outputToFile("helloworld.pdf",pdfcontent,"ISO-8859-1");
    }
}
