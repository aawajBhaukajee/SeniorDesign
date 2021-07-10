// Generated by view binder compiler. Do not edit!
package com.example.seniordesign.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.example.seniordesign.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ListSingHospitalsBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView listemailH;

  @NonNull
  public final TextView listlocationH;

  @NonNull
  public final TextView listnameH;

  @NonNull
  public final TextView textView10;

  @NonNull
  public final TextView textView11;

  @NonNull
  public final TextView textView9;

  private ListSingHospitalsBinding(@NonNull CardView rootView, @NonNull TextView listemailH,
      @NonNull TextView listlocationH, @NonNull TextView listnameH, @NonNull TextView textView10,
      @NonNull TextView textView11, @NonNull TextView textView9) {
    this.rootView = rootView;
    this.listemailH = listemailH;
    this.listlocationH = listlocationH;
    this.listnameH = listnameH;
    this.textView10 = textView10;
    this.textView11 = textView11;
    this.textView9 = textView9;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ListSingHospitalsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ListSingHospitalsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.list_sing_hospitals, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ListSingHospitalsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.listemailH;
      TextView listemailH = rootView.findViewById(id);
      if (listemailH == null) {
        break missingId;
      }

      id = R.id.listlocationH;
      TextView listlocationH = rootView.findViewById(id);
      if (listlocationH == null) {
        break missingId;
      }

      id = R.id.listnameH;
      TextView listnameH = rootView.findViewById(id);
      if (listnameH == null) {
        break missingId;
      }

      id = R.id.textView10;
      TextView textView10 = rootView.findViewById(id);
      if (textView10 == null) {
        break missingId;
      }

      id = R.id.textView11;
      TextView textView11 = rootView.findViewById(id);
      if (textView11 == null) {
        break missingId;
      }

      id = R.id.textView9;
      TextView textView9 = rootView.findViewById(id);
      if (textView9 == null) {
        break missingId;
      }

      return new ListSingHospitalsBinding((CardView) rootView, listemailH, listlocationH, listnameH,
          textView10, textView11, textView9);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
