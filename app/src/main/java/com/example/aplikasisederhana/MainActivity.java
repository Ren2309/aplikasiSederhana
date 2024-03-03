package com.example.aplikasisederhana;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroupBarang1, radioGroupBarang2, radioGroupBarang3, radioGroupMembership;
    RadioButton radioBarang1Harga1, radioBarang1Harga2, radioBarang2Harga1, radioBarang3Harga1, radioMember;
    Button btnProses;
    TextView tvNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupBarang1 = findViewById(R.id.radioGroupBarang1);
        radioGroupBarang2 = findViewById(R.id.radioGroupBarang2);
        radioGroupBarang3 = findViewById(R.id.radioGroupBarang3);
        radioGroupMembership = findViewById(R.id.radioGroupMembership);

        radioBarang1Harga1 = findViewById(R.id.radioBarang1Harga1);
        radioBarang1Harga2 = findViewById(R.id.radioBarang1Harga2);
        radioBarang2Harga1 = findViewById(R.id.radioBarang2Harga1);
        radioBarang3Harga1 = findViewById(R.id.radioBarang3Harga1);
        radioMember = findViewById(R.id.radioMember);

        btnProses = findViewById(R.id.btnProses);
        tvNota = findViewById(R.id.tvNota);

        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalHarga = 0;

                // Hitung total harga berdasarkan pilihan barang
                if (radioBarang1Harga1.isChecked()) {
                    totalHarga += 49500;
                } else if (radioBarang1Harga2.isChecked()) {
                    totalHarga += 50000; // Adjust the price as needed
                }

                if (radioBarang2Harga1.isChecked()) {
                    totalHarga += 9500;
                }

                if (radioBarang3Harga1.isChecked()) {
                    totalHarga += 10000;
                }

                // Tambahkan biaya admin (sesuai logic yang diminta)
                totalHarga += getAdminFee();

                // Check jika membership dipilih
                if (radioMember.isChecked()) {
                    // Diskon 5%
                    totalHarga *= 0.95;
                }

                // Tampilkan bon
                tvNota.setText("Nama Barang: " + getSelectedBarang() +
                        "\nBanyak Barang: 1" +
                        "\nTotal Harga Barang: Rp " + totalHarga +
                        "\nBiaya Admin: Rp " + getAdminFee() +
                        "\nDiskon: " + (radioMember.isChecked() ? "5%" : "0%") +
                        "\nPemotongan: Rp " + getDiscountAmount(totalHarga) +
                        "\nTotal Bayar: Rp " + (totalHarga - getDiscountAmount(totalHarga)));
            }
        });
    }

    private double getAdminFee() {
        // Implement your logic for determining admin fee based on the selected item
        // For now, I'll assume a fixed admin fee of 2000.
        return 2000;
    }

    private String getSelectedBarang() {
        // Implement your logic to get the selected item name based on RadioGroups
        // For now, I'll return a generic name. Adjust as needed.
        if (radioBarang1Harga1.isChecked()) {
            return "Item 1";
        } else if (radioBarang2Harga1.isChecked()) {
            return "Item 2";
        } else if (radioBarang3Harga1.isChecked()) {
            return "Item 3";
        } else {
            return "Unknown Item";
        }
    }

    private double getDiscountAmount(double totalHarga) {
        // Calculate the discount amount based on the totalHarga and membership discount
        return radioMember.isChecked() ? totalHarga * 0.05 : 0;
    }
}
