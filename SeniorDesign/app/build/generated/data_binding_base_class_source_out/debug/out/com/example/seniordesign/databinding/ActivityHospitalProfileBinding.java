// Generated by view binder compiler. Do not edit!
package com.example.seniordesign.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.seniordesign.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityHospitalProfileBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView mainEmailH;

  @NonNull
  public final TextView mainLocationH;

  @NonNull
  public final TextView mainNameH;

  @NonNull
  public final TextView profileH;

  @NonNull
  public final TextView textView12;

  @NonNull
  public final TextView textView13;

  @NonNull
  public final TextView textView14;

  private ActivityHospitalProfileBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView mainEmailH, @NonNull TextView mainLocationH, @NonNull TextView mainNameH,
      @NonNull TextView profileH, @NonNull TextView textView12, @NonNull TextView textView13,
      @NonNull TextView textView14) {
    this.rootView = rootView;
    this.mainEmailH = mainEmailH;
    this.mainLocationH = mainLocationH;
    this.mainNameH = mainNameH;
    this.profileH = profileH;
    this.textView12 = textView12;
    this.textView13 = textView13;
    this.textView14 = textView14;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityHospitalProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityHospitalProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_hospital_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityHospitalProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.mainEmailH;
      TextView mainEmailH = rootView.findViewById(id);
      if (mainEmailH == null) {
        break missingId;
      }

      id = R.id.mainLocationH;
      TextView mainLocationH = rootView.findViewById(id);
      if (mainLocationH == null) {
        break missingId;
      }

      id = R.id.mainNameH;
      TextView mainNameH = rootView.findViewById(id);
      if (mainNameH == null) {
        break missingId;
      }

      id = R.id.profileH;
      TextView profileH = rootView.findViewById(id);
      if (profileH == null) {
        break missingId;
      }

      id = R.id.textView12;
      TextView textView12 = rootView.findViewById(id);
      if (textView12 == null) {
        break missingId;
      }

      id = R.id.textView13;
      TextView textView13 = rootView.findViewById(id);
      if (textView13 == null) {
        break missingId;
      }

      id = R.id.textView14;
      TextView textView14 = rootView.findViewById(id);
      if (textView14 == null) {
        break missingId;
      }

      return new ActivityHospitalProfileBinding((ConstraintLayout) rootView, mainEmailH,
          mainLocationH, mainNameH, profileH, textView12, textView13, textView14);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
