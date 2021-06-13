// Generated by view binder compiler. Do not edit!
package com.example.seniordesign.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.seniordesign.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityFirstPageBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button hRegister;

  @NonNull
  public final Button uRegister;

  private ActivityFirstPageBinding(@NonNull ConstraintLayout rootView, @NonNull Button hRegister,
      @NonNull Button uRegister) {
    this.rootView = rootView;
    this.hRegister = hRegister;
    this.uRegister = uRegister;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityFirstPageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityFirstPageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_first_page, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityFirstPageBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.hRegister;
      Button hRegister = rootView.findViewById(id);
      if (hRegister == null) {
        break missingId;
      }

      id = R.id.uRegister;
      Button uRegister = rootView.findViewById(id);
      if (uRegister == null) {
        break missingId;
      }

      return new ActivityFirstPageBinding((ConstraintLayout) rootView, hRegister, uRegister);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
