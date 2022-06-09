package ru.mirea.ochirgoryaeva.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private String filename;
    private EditText etName;
    private EditText etFile;
    private SharedPreferences preferences;
    final String FileName = "File_name";
    final String Empty = "Empty";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.editTextName);
        etFile = findViewById(R.id.editTextFile);
        preferences = getPreferences(MODE_PRIVATE);

    }
    public void onSaveText(View view) {
        filename = etName.getText().toString();
        setTextToFile();
        SharedPreferences.Editor editor = preferences.edit();
// Сохранение значения по ключу "File_name"
        editor.putString(FileName, filename);
        editor.apply();
        //Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }
    public void onLoadText(View view) {
// Загрузка значения по ключу "File_name"
        filename = preferences.getString(FileName, Empty);
        if (filename != Empty) {
            etFile.setText(getTextFromFile());
        } else {
            etFile.setText("File is empty");

        }
    }
    public void setTextToFile() {
        FileOutputStream fos = null;
        try {
            String text = etFile.getText().toString();

            fos = openFileOutput(filename, MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this, "File saved", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public String getTextFromFile() {
        FileInputStream fins = null;
        try {
            fins = openFileInput(filename);
            byte[] bytes = new byte[fins.available()];
            fins.read(bytes);
            String text = new String(bytes);
            Log.d(LOG_TAG, text);
            Toast.makeText(this, "Text from the last file", Toast.LENGTH_SHORT).show();
            return text;
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fins != null)
                    fins.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }



}