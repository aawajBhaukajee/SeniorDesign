// Generated by view binder compiler. Do not edit!
package com.example.seniordesign.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.seniordesign.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button hospList;

  @NonNull
  public final Button logoutButton;

  @NonNull
  public final TextView mainAge;

  @NonNull
  public final TextView mainEmail;

  @NonNull
  public final TextView mainHBP;

  @NonNull
  public final TextView mainLBP;

  @NonNull
  public final TextView mainName;

  @NonNull
  public final TextView mainWeight;

  @NonNull
  public final TextView mainbloodType;

  @NonNull
  public final TextView profile;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextView textView5;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final TextView welcome;

  private ActivityMainBinding(@NonNull ConstraintLayout rootView, @NonNull Button hospList,
      @NonNull Button logoutButton, @NonNull TextView mainAge, @NonNull TextView mainEmail,
      @NonNull TextView mainHBP, @NonNull TextView mainLBP, @NonNull TextView mainName,
      @NonNull TextView mainWeight, @NonNull TextView mainbloodType, @NonNull TextView profile,
      @NonNull TextView textView, @NonNull TextView textView2, @NonNull TextView textView3,
      @NonNull TextView textView4, @NonNull TextView textView5, @NonNull TextView textView6,
      @NonNull TextView textView7, @NonNull TextView welcome) {
    this.rootView = rootView;
    this.hospList = hospList;
    this.logoutButton = logoutButton;
    this.mainAge = mainAge;
    this.mainEmail = mainEmail;
    this.mainHBP = mainHBP;
    this.mainLBP = mainLBP;
    this.mainName = mainName;
    this.mainWeight = mainWeight;
    this.mainbloodType = mainbloodType;
    this.profile = profile;
    this.textView = textView;
    this.textView2 = textView2;
    this.textView3 = textView3;
    this.textView4 = textView4;
    this.textView5 = textView5;
    this.textView6 = textView6;
    this.textView7 = textView7;
    this.welcome = welcome;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.hospList;
      Button hospList = rootView.findViewById(id);
      if (hospList == null) {
        break missingId;
      }

      id = R.id.logoutButton;
      Button logoutButton = rootView.findViewById(id);
      if (logoutButton == null) {
        break missingId;
      }

      id = R.id.mainAge;
      TextView mainAge = rootView.findViewById(id);
      if (mainAge == null) {
        break missingId;
      }

      id = R.id.mainEmail;
      TextView mainEmail = rootView.findViewById(id);
      if (mainEmail == null) {
        break missingId;
      }

      id = R.id.mainHBP;
      TextView mainHBP = rootView.findViewById(id);
      if (mainHBP == null) {
        break missingId;
      }

      id = R.id.mainLBP;
      TextView mainLBP = rootView.findViewById(id);
      if (mainLBP == null) {
        break missingId;
      }

      id = R.id.mainName;
      TextView mainName = rootView.findViewById(id);
      if (mainName == null) {
        break missingId;
      }

      id = R.id.mainWeight;
      TextView mainWeight = rootView.findViewById(id);
      if (mainWeight == null) {
        break missingId;
      }

      id = R.id.mainbloodType;
      TextView mainbloodType = rootView.findViewById(id);
      if (mainbloodType == null) {
        break missingId;
      }

      id = R.id.profile;
      TextView profile = rootView.findViewById(id);
      if (profile == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = rootView.findViewById(id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = rootView.findViewById(id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = rootView.findViewById(id);
      if (textView3 == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = rootView.findViewById(id);
      if (textView4 == null) {
        break missingId;
      }

      id = R.id.textView5;
      TextView textView5 = rootView.findViewById(id);
      if (textView5 == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = rootView.findViewById(id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = rootView.findViewById(id);
      if (textView7 == null) {
        break missingId;
      }

      id = R.id.welcome;
      TextView welcome = rootView.findViewById(id);
      if (welcome == null) {
        break missingId;
      }

      return new ActivityMainBinding((ConstraintLayout) rootView, hospList, logoutButton, mainAge,
          mainEmail, mainHBP, mainLBP, mainName, mainWeight, mainbloodType, profile, textView,
          textView2, textView3, textView4, textView5, textView6, textView7, welcome);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
