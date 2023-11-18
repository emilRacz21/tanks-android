package com.example.kolko_krzyzyk;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    ImageView cellButton;

    TextView points;
    int[] randomIndex = new int[4];

    int result = 0;

    ImageView [] cells = new ImageView[25];
    int randomLength = (int) Math.round(Math.random() * 3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        points = findViewById(R.id.missPoints);


        final int[][] WINNING_VARIANTS = {
                {0, 5, 10, 11},
                {20,21},
                {3, 8, 13, 14},
                {23,24},
        };



        if (randomLength < 1) {
            randomLength = 1;
        }

        // Inicjalizacja tablicy o losowej długości
        int[] tab = new int[randomLength];

        // Losowanie indeksów z tablicy WINNING_VARIANTS
        for (int i = 0; i < randomLength; i++) {
            int randomIdx;
            boolean isDuplicate;

            do {
                randomIdx = (int) Math.round(Math.random() * (WINNING_VARIANTS.length - 1));
                isDuplicate = false;

                // Sprawdź, czy wylosowany indeks już istnieje w tablicy
                for (int j = 0; j < i; j++) {
                    if (randomIdx == tab[j]) {
                        isDuplicate = true;
                        break;
                    }
                }
            } while (isDuplicate);

            // Dodaj unikalny indeks do tablicy
            tab[i] = randomIdx;
        }

        System.out.println("Losowa długość tablicy: " + randomLength);
        // Wypisanie wylosowanych indeksów
        System.out.print("Losowe indeksy: ");
        for (int i = 0; i < tab.length; i++) {
            System.out.print(tab[i] + " ");
            randomIndex[i] = tab[i];
        }



        getCells();
        View.OnClickListener onCellClickListener = v -> {
            cellButton = findViewById(v.getId());
            boolean znaleziono = false;


            for (int i = 0; i < randomLength; i++) {
                System.out.println(randomIndex[i]);

                int[] wariant = WINNING_VARIANTS[randomIndex[i]];

                for (int j = 0; j < wariant.length; j++) {
                    int idKomorki = wariant[j];

                    if (idKomorki == cellButton.getId()) {
                        znaleziono = true;
                        cellButton.setClickable(false);
                        break;
                    }
                }

                if (znaleziono) {
                    points.setTextColor(Color.parseColor("#FF27C12D"));
                    cellButton.setBackgroundResource(R.drawable.tank);
                    result +=5;


                } else {
                    points.setTextColor(Color.parseColor("#FFCA061D"));
                    cellButton.setBackgroundResource(R.drawable.cross);
                    result -=1;

                }
                points.setText(String.valueOf(result));
                cellButton.setClickable(false);
            }

        };

        sendCells( onCellClickListener );
    }

    //Pobierz buttony z activity_main.
    void getCells() {
        int[] img = {
                R.id.cell1, R.id.cell2, R.id.cell3,
                R.id.cell4, R.id.cell5, R.id.cell6,
                R.id.cell7, R.id.cell8, R.id.cell9,
                R.id.cell10, R.id.cell11, R.id.cell12,
                R.id.cell13, R.id.cell14, R.id.cell15,
                R.id.cell16, R.id.cell17,  R.id.cell18,
                R.id.cell19, R.id.cell20, R.id.cell21,
                R.id.cell22, R.id.cell23, R.id.cell24,
                R.id.cell25,
        };
        for ( int i = 0 ; i < cells.length ; i ++){
            cells[i] = findViewById(img[i]);
            cells[i].setId(i);
        }

    }
    //Wyslij buttony do nasluchu klikniecia do view onCellClickListener.
    void sendCells( View.OnClickListener onCellClickListener ) {
        for (ImageView cell : cells) cell.setOnClickListener(onCellClickListener);
    }
}
