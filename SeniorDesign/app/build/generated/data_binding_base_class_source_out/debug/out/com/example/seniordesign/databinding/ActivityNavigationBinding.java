// Generated by view binder compiler. Do not edit!
package com.example.seniordesign.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewbinding.ViewBinding;
import com.example.seniordesign.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityNavigationBinding implements ViewBinding {
  @NonNull
  private final DrawerLayout rootView;

  @NonNull
  public final BottomNavigationView bottomNavigation;

  @NonNull
  public final DrawerLayout drawerLayout;

  @NonNull
  public final TextView idWelcome;

  @NonNull
  public final MainToolbarBinding include2;

  @NonNull
  public final ConstraintLayout linearLayout;

  @NonNull
  public final TextView userName;

  private ActivityNavigationBinding(@NonNull DrawerLayout rootView,
      @NonNull BottomNavigationView bottomNavigation, @NonNull DrawerLayout drawerLayout,
      @NonNull TextView idWelcome, @NonNull MainToolbarBinding include2,
      @NonNull ConstraintLayout linearLayout, @NonNull TextView userName) {
    this.rootView = rootView;
    this.bottomNavigation = bottomNavigation;
    this.drawerLayout = drawerLayout;
    this.idWelcome = idWelcome;
    this.include2 = include2;
    this.linearLayout = linearLayout;
    this.userName = userName;
  }

  @Override
  @NonNull
  public DrawerLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityNavigationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityNavigationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_navigation, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityNavigationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bottom_navigation;
      BottomNavigationView bottomNavigation = rootView.findViewById(id);
      if (bottomNavigation == null) {
        break missingId;
      }

      DrawerLayout drawerLayout = (DrawerLayout) rootView;

      id = R.id.id_welcome;
      TextView idWelcome = rootView.findViewById(id);
      if (idWelcome == null) {
        break missingId;
      }

      id = R.id.include2;
      View include2 = rootView.findViewById(id);
      if (include2 == null) {
        break missingId;
      }
      MainToolbarBinding binding_include2 = MainToolbarBinding.bind(include2);

      id = R.id.linearLayout;
      ConstraintLayout linearLayout = rootView.findViewById(id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.userName;
      TextView userName = rootView.findViewById(id);
      if (userName == null) {
        break missingId;
      }

      return new ActivityNavigationBinding((DrawerLayout) rootView, bottomNavigation, drawerLayout,
          idWelcome, binding_include2, linearLayout, userName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}