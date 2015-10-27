package com.hammersmith.fustalfootballbookingfield;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by USER on 9/18/2015.
 */
public class tab_fixture extends RootFragmgmet {
    TextView textWeeks, textClubs;
    ImageView week, club;
    public static EditText textWeek, textClub;
    String Value = "";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fixture = inflater.inflate(R.layout.tab_fixture,container,false);

        textWeeks = (TextView)fixture.findViewById(R.id.textWeek);
        week = (ImageView)fixture.findViewById(R.id.imageWeek);
        week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedWeek();
            }
        });

        textClubs = (TextView)fixture.findViewById(R.id.textClub);
        club = (ImageView)fixture.findViewById(R.id.imageClub);
        club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedClub();
            }
        });

        return fixture;
    }

    private void selectedClub() {
        Dialog dialog;
        final int[] mSelected = {-1};
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Week");
        builder.setCancelable(true);
        final String[] weeks = new String[]{"English Premire League","Barclays Premire League","Span Premire League",
                "Laliga Premire League","Euro Premire League","Germany Premire League"};
        final DialogInterface.OnMultiChoiceClickListener onClik =
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked){
                            if((mSelected[0] != -1) && (mSelected[0] != which)){
                                final int oldVal = mSelected[0];
                                final AlertDialog alert = (AlertDialog)dialog;
                                final ListView list = alert.getListView();
                                list.setItemChecked(oldVal,false);
                            }
                            mSelected[0] = which;
                        }else
                            mSelected[0] = -1;
                    }
                };
        builder.setMultiChoiceItems(weeks,null,onClik);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = null;
                if(mSelected[0] == -1)
                    message = "You didn't select!";
                else
                    message = weeks[mSelected[0]];
                textClubs.setText(message);
                //Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();

            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        dialog = builder.create();
        dialog.show();
    }

    private void selectedWeek() {
        Dialog dialog;
        final int[] mSelected = {-1};
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Club");
        builder.setCancelable(true);
        final String[] weeks = new String[]{"Week 1","Week 2","Week 3","Week 4",
                                            "Week 5","Week 6","Week 7","Week 8",
                                            "Week 9","Week 10","Week 11","Week 12"};
        final DialogInterface.OnMultiChoiceClickListener onClik =
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked){
                            if((mSelected[0] != -1) && (mSelected[0] != which)){
                                final int oldVal = mSelected[0];
                                final AlertDialog alert = (AlertDialog)dialog;
                                final ListView list = alert.getListView();
                                list.setItemChecked(oldVal,false);
                            }
                            mSelected[0] = which;
                        }else
                            mSelected[0] = -1;
                    }
                };
                builder.setMultiChoiceItems(weeks,null,onClik);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String message = null;
                        if(mSelected[0] == -1)
                            message = "You didn't select!";
                        else
                            message = weeks[mSelected[0]];
                        textWeeks.setText(message);
                        //Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog = builder.create();
                dialog.show();
    }
}
