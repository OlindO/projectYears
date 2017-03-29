package fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.yanis.projectbookshopsw.R;
import com.google.gson.Gson;
import Class.Personne;
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CompteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CompteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
//---------------------------------------------------------------------------------------
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private Personne personne;
    private ViewSwitcher viewSwitcherNom, viewSwitcherPrenom,viewSwitcherEmail,viewSwitcherPassword;
    private TextView txtnom, txtprenom,txtemail, txtpassword;
    private Button btnLog;
    private String sNom, sPrenom,sEmail, sPassword;
// --------------------------------------------------------------------------------------

    public CompteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CompteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompteFragment newInstance(String param1, String param2) {
        CompteFragment fragment = new CompteFragment();
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
        // RECEIVE DATA FROM PERSONNES-
        sharedPreferences = getActivity().getSharedPreferences("SHARED_PREFERENCES", Context.MODE_PRIVATE);
        gson = new Gson();

        String json = sharedPreferences.getString("infoUserConnected", "");
        personne = gson.fromJson(json, Personne.class);
        // -----------------------------


       /* DrawerLayout drawer = (DrawerLayout)getActivity().findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();*/

        txtnom = (TextView)getActivity().findViewById(R.id.txtNom);
        txtprenom = (TextView)getActivity().findViewById(R.id.txtPrenom);
        txtemail = (TextView)getActivity().findViewById(R.id.txtEmail);
        txtpassword = (TextView)getActivity().findViewById(R.id.txtPassword);
        viewSwitcherNom = (ViewSwitcher)getActivity().findViewById(R.id.idSwitcherNom);
        viewSwitcherPrenom= (ViewSwitcher)getActivity().findViewById(R.id.idSwitcherPrenom);
        viewSwitcherEmail = (ViewSwitcher)getActivity().findViewById(R.id.idSwitcherEmail);
        viewSwitcherPassword = (ViewSwitcher)getActivity().findViewById(R.id.idSwitcherPassword);
        btnLog=(Button)getActivity().findViewById(R.id.btnLogin);

        if(personne != null)
        {
            txtnom.setText(personne.getNom());
            txtprenom.setText(personne.getPrenom());
            txtemail.setText(personne.getEmail());
            txtpassword.setText(personne.getPassword());
        }
        else
        {
            Toast.makeText(getActivity(), "Champs incorrect, Ressayez ", Toast.LENGTH_SHORT).show();
        }

        txtnom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewSwitcherNom.showNext();
            }
        });
        txtprenom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewSwitcherPrenom.showNext();
            }
        });
        txtemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewSwitcherEmail.showNext();
            }
        });
        txtpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewSwitcherPassword.showNext();
            }
        });
        sNom = txtnom.getText().toString().trim();
        sPrenom = txtprenom.getText().toString().trim();
        sEmail = txtemail.getText().toString().trim();
        sPassword = txtpassword.getText().toString().trim();

        if((sNom.length() >0 ) && (sPrenom.length() >0) && (sEmail.length() >0)
                && (sPassword.length() >0))
        {
            //btnLog.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compte, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            /*throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}