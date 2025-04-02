package praktikum2;

public class InfoPelanggan {
    private String nama;
    private String nomorPelanggan;
    private double saldo;
    private String pin;
    private boolean diblokir;
    private int kesalahanLogin;

    public InfoPelanggan(String nama, String nomorPelanggan, double saldo, String pin) {
        //berisi nama pelanggan, this merujuk ke atribut private string nama. Jadi this digunakan untuk membedakan antara atribut dan parameter
        this.nama = nama;
        this.nomorPelanggan = nomorPelanggan;
        this.saldo = saldo;
        this.pin = pin;
        this.diblokir = false;
        this.kesalahanLogin = 0;
    }

    public boolean autentikasi(String nomor, String pin) {
        //ngecek apakah akun sudah diblokir, kalo iya return false
        if (diblokir) {
            System.out.println("Akun telah diblokir.");
            return false;
        }
        //kalo pin yang dimasukkan sudah benar, maka akan mereset kesalahan Login dan mengembalikan true 
        if (this.nomorPelanggan.equals(nomor) && this.pin.equals(pin)) {
            kesalahanLogin = 0;
            return true;
        } else {
            //kalo pin salah, maka akan menambahkan 1 kesalahan login
            kesalahanLogin++;
            System.out.println("PIN salah! Percobaan: " + kesalahanLogin);
            //klo kesalahanLogin udah lebih dari sama dengan 3, maka diblokir akan berubah menjadi true
            if (kesalahanLogin >= 3) {
                diblokir = true;
                System.out.println("Akun telah diblokir setelah 3 kali kesalahan.");
            }
            return false;
        }
    }

    public void topUp(double jumlah) {
        if (jumlah > 0) {
            //kalo user ingin top up, maka nominal akan ditambahkan ke dalam saldo user
            saldo += jumlah;
            System.out.println("Top-up berhasil!");
            System.out.println("===============================");
            System.out.println("Saldo saat ini: Rp " + saldo);
        } else {
            System.out.println("Jumlah top-up tidak valid.");
        }
    }

    public void beli(double harga) {
        //kalo akun diblokir, maka transaksi ditolak
        if (diblokir) {
            System.out.println("Transaksi ditolak, akun diblokir.");
            return;
        }
        
        //menghitung cash back berasarkan jenis nomor pelanggan yang dimiliki
        double cashback = hitungCashback(harga);
        //tiap total biaya akan dikurangi oleh harga barang dengan cashback yang dimiliki oleh pelanggan
        double totalBiaya = harga - cashback;
        
        //jika saldo - total biaya lebih besar dari 10k, maka transaksi akan berhasil
        if (saldo - totalBiaya >= 10000) {
            saldo -= totalBiaya;
            System.out.println("Pembelian berhasil!");
            System.out.println("===============================");
            System.out.println("Cashback: Rp " + cashback);
            System.out.println("Saldo saat ini: Rp " + saldo);
        } else {
            System.out.println("Saldo tidak mencukupi. Minimal saldo setelah transaksi adalah Rp10.000.");
        }
    }

    private double hitungCashback(double harga) {
        //kalo harga suatu barang kurang dari 1 juta, cashback tidak akan diberikan
        if (harga < 1000000) return 0;
        
        String kodeJenis = nomorPelanggan.substring(0, 2);
        switch (kodeJenis) {
            //khusus buat kode 38, setiap pembelian di atas 1 juta akan mendapatkan cashback sebesar 5% dari harga barang
            case "38": 
                        return harga * 0.05;
            
            //kode 56 akan mendapatkan cashback sebesar 7%  dari harga barang jika pembelian di atas 1 juta
            //jika di bawah 1 juta, hanya akan mendapat cashback 2% dari harga barang
            case "56": 
                        if (harga >= 1000000) {
                           return harga * 0.07;
                        } else {
                            return harga * 0.02;
                        }
            
            //kode 74 akan mendapatkan cashback sebesar 10% dari harga barang jika pembelian di atas 1 juta
            //jika di bawah 1 juta, hanya akan mendapat cashback 5% dari harga barang
            case "74": 
                    if (harga >= 1000000) {
                        return harga * 0.10;
                    } else {
                        return harga * 0.05;
                    }
            default: return 0;
        }
    }
}


