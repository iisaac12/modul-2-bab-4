package praktikum2;

import java.util.Scanner;

public class SwalayanAmba {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //membuat objek infoPelanggan yang memiliki atribut nama Eca, nomorPelanggan 3812345678 dengan pin 030706
        InfoPelanggan infoPelanggan = new InfoPelanggan("Eca", "3812345678", 900000, "030706");

        System.out.println("Selamat datang di Swalayan kesayangan kamu, Swalayan Amba!");
        System.out.println("===============================");

        while (true) {
            System.out.println("Silahkan pilih menu: ");
            System.out.println("1. Beli Barang");
            System.out.println("2. Top Up Saldo");
            System.out.println("3. Keluar");
            System.out.print("Pilihan kamu: ");
            int pilihan = input.nextInt();

            if(pilihan == 3) break;

            System.out.print("Masukkan nomer pelanggan: ");
            String nomer = input.next();
            System.out.print("Masukkan PIN kamu: ");
            String pin = input.next();

            //obejk tadi dicek dengan method autentikasi (nomer dan pin), klo salah maka program akan lompat ke loop awal
            if (!infoPelanggan.autentikasi(nomer, pin)) continue;
            
            if (pilihan == 1) {
                System.out.print("Masukkkan harga barang: Rp. ");
                double hargaBarang = input.nextDouble();
                infoPelanggan.beli(hargaBarang);
                System.out.println("===============================");

            } else if (pilihan == 2){
                System.out.print("Masukkan nominal top up: Rp. ");
                double nominal = input.nextDouble();
                infoPelanggan.topUp(nominal);
                System.out.println("===============================");
            } else {
                System.out.println("Pilihan kamu tidak valid!");
            }
        }
    input.close();
    System.out.println("=====================================================");
    System.out.println("Terima kasih telah berbelanja di Swalayan Amba, muach");
    System.out.println("=====================================================");
    }
}
