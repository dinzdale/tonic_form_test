package com.design.tonic.tonicformtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText firstname;
    private EditText lastname;
    private Button format_button;
    private TextView fullname;
    private int lastSelectedOption = R.id.firstname_lastname_menu_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstname = (EditText) findViewById(R.id.first_name_edittext);
        firstname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                enableDisableFormatButton();
            }
        });
        lastname = (EditText) findViewById(R.id.last_name_edittext);
        lastname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                enableDisableFormatButton();
            }
        });
        fullname = (TextView) findViewById(R.id.fullname_textview);
        format_button = (Button) findViewById(R.id.format_button);
        format_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });
        format_button.setEnabled(false);
    }


    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.format_popup_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.firstname_lastname_menu_id:
                        lastSelectedOption = R.id.firstname_lastname_menu_id;
                        setFullName();
                        break;
                    case R.id.lastname_firstname_menu_id:
                        lastSelectedOption = R.id.lastname_firstname_menu_id;
                        setFullName();
                        break;
                }
                return false;
            }
        });
        popup.show();
    }

    private void enableDisableFormatButton() {
        if (firstname.getText().length() > 0 && lastname.getText().length() > 0) {
            format_button.setEnabled(true);
            setFullName();
        } else {
            fullname.setText("");
            format_button.setEnabled(false);
        }
    }

    private void setFullName() {
        switch (lastSelectedOption) {
            case R.id.firstname_lastname_menu_id:
                fullname.setText(getString(R.string.firstname_lastname_formatted, firstname.getText(), lastname.getText()));
                break;
            case R.id.lastname_firstname_menu_id:
                fullname.setText(getString(R.string.lastname_firstname_formatted, lastname.getText(), firstname.getText()));
                break;
        }
    }
}
