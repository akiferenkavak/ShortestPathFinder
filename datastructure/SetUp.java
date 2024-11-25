import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SetUp {

    public static int[][] get2DArrayFromCSV(String filePath) {
        int[][] array; // 2D dizi tanımı
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // İlk satırın uzunluğunu belirlemek için dosyayı bir kez okuyup satır sayısını buluyoruz
            String line;
            int rows = 0;
            int cols = 0;

            while ((line = br.readLine()) != null) {
                if (rows == 0) { // İlk satırın eleman sayısını sütun sayısı olarak belirle
                    cols = line.split(",").length;
                }
                rows++;
            }

            // 2D diziyi oluştur
            array = new int[rows - 1][cols - 1]; // İlk satır ve sütun atlandığı için boyutlar küçüldü

            // Dosyayı tekrar oku ve 2D diziye aktar
            BufferedReader br2 = new BufferedReader(new FileReader(filePath));
            int row = 0;
            
            while ((line = br2.readLine()) != null) {
                // İlk satırı atla
                if (row == 0) {
                    row++;
                    continue;
                }
            
                // Satırı ayır
                String[] values = line.split(",");
            
                // İlk sütunu atlayarak 2D diziye elemanları ekle
                for (int col = 1; col < values.length; col++) { // col=1 ile ilk sütunu atlıyoruz
                    array[row - 1][col - 1] = Integer.parseInt(values[col]); // İlk sütun atlandığı için index ayarlaması
                }
                row++;
            }
            br2.close();

            return array;

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Hata durumunda null döndür
        return null;
    }

    public static void main(String[] args) {
        // Örnek kullanımı
        String filePath = "Turkish cities.csv"; // CSV dosyasının yolu
        int[][] result = get2DArrayFromCSV(filePath);

        // 2D diziyi yazdır (kontrol amaçlı)
        if (result != null) {
            System.out.println("CSV'den okunan 2D dizi:");
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    System.out.print(result[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("CSV dosyası okunamadı.");
        }
    }


    

}
