package ru.mirea.ochirgoryaeva.mireaproject;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText edtName;
    private EditText edtProfession;
    private TextView tv1;
    public Button btnSave;
    private SharedPreferences preferences;
    final String SAVED_SETTINGS = "saved_settings";
    Activity activity;
    RecyclerView recyclerView;
    SettAdapter adapter;
    ArrayList<SettingsList> settingsLists = new ArrayList<SettingsList>();

    private String TAG = MainActivity.class.getSimpleName();

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        edtName = v.findViewById(R.id.edtName);
        edtProfession = v.findViewById(R.id.edtProfession);
        preferences = getActivity().getPreferences(MODE_PRIVATE);
        btnSave = v.findViewById(R.id.btnSaveSettings);

        //preferences = getActivity().getPreferences(MODE_PRIVATE);
        // LinearLayoutManager mRecentLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView = v.findViewById(R.id.recyclerView);
        //recyclerView.setLayoutManager(mRecentLayoutManager);
        // создаем адаптер
        adapter = new SettAdapter(getActivity(),settingsLists);
        // устанавливаем для списка адаптер

        btnSave.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {

        recyclerView.setAdapter(adapter);
        setInitialData();

        SharedPreferences.Editor editor = preferences.edit();
// Сохранение значения по ключу SAVED_SETTINGS

        String editorItog = edtName.getText().toString() +" "+ edtProfession.getText().toString();
        editor.putString(SAVED_SETTINGS, editorItog);
        editor.apply();
        Toast.makeText(getActivity(), "Settings saved", Toast.LENGTH_SHORT).show();
        /*String stringName = edtName.getText().toString();
        String stringPost = edtProfession.getText().toString();
        Toast.makeText(getActivity(), "Settings saved", Toast.LENGTH_SHORT).show();
        */

        /*String text = preferences.getString(SAVED_SETTINGS, "Empty");
        tv1.setText(text);*/
    }
    private void setInitialData(){


        String content = preferences.getString(SAVED_SETTINGS, "Empty");
        if(content!=null) {
            settingsLists.add(new SettingsList(content));
            Log.d(TAG, "SettingsList add");

            /*String[] nameList = content.split(" ");
            for (int i = 0; i < nameList.length; i++) {
                settingsLists.add(new SettingsList(nameList[i], preferences.getString(nameList[i],  "str")));
            }*/
        }
    }


}