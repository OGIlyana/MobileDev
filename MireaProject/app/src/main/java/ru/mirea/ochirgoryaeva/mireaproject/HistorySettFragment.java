package ru.mirea.ochirgoryaeva.mireaproject;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistorySettFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistorySettFragment extends Fragment implements Postman{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Fragment fragmentSett;
    FragmentManager fragmentManager;
    private SharedPreferences preferences;
    ArrayList<SettingsList> settingsLists = new ArrayList<SettingsList>();
    SettAdapter adapter;
    RecyclerView recyclerView;
    final String SAVED_SETTINGS = "saved_settings";


    public HistorySettFragment () {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistorySettFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistorySettFragment newInstance(String param1, String param2) {
        HistorySettFragment fragment = new HistorySettFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history_sett, container, false);

        fragmentSett = new SettingsFragment();
        preferences = getActivity().getPreferences(MODE_PRIVATE);
        setInitialData();
        LinearLayoutManager mRecentLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(mRecentLayoutManager);
        // создаем адаптер
        adapter = new SettAdapter(getActivity(),settingsLists);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
        // Inflate the layout for this fragment
        return v;
    }
    private void setInitialData(){

        /*String content = getTextFromFile();
        if(content!=null) {
            String[] nameList = content.split("\n");
            for (int i = 0; i < nameList.length; i++) {
                settingsLists.add(new SettingsList(nameList[i], preferences.getString(nameList[i],  "str")));
            }
        }*/
    }
    public void AddSetting(View view)
    {
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer,
                fragmentSett).commit();
    }

    @Override
    public void fragmentMail(String name, String post) {
        SharedPreferences.Editor editor = preferences.edit();
        // Сохранение значения по ключу
        String editorItog = name +" "+ post;
        editor.putString(SAVED_SETTINGS, editorItog);
        editor.apply();
        //settingsLists.add(new SettingsList(name,post));
        adapter.notifyItemChanged(0);
        setTextToFile(name);
    }

    public void setTextToFile(String key) {
        String content = getTextFromFile()+key+" ";
        FileOutputStream fos = null;
        try {
            fos = getActivity().openFileOutput("posts.txt", MODE_PRIVATE);
            fos.write(content.getBytes());
            //Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {

            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public String getTextFromFile() {
        FileInputStream fin = null;
        try {
            fin = getActivity().openFileInput("posts.txt");
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            return text;
        } catch (IOException ex) {
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }


}