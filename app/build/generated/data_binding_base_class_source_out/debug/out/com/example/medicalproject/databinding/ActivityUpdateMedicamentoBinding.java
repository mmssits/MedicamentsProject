// Generated by view binder compiler. Do not edit!
package com.example.medicalproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.medicalproject.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityUpdateMedicamentoBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonCor;

  @NonNull
  public final Button buttonDelete;

  @NonNull
  public final Button buttonUpdate;

  @NonNull
  public final EditText editTextDesc;

  @NonNull
  public final EditText editTextDose;

  @NonNull
  public final EditText editTextHoras;

  @NonNull
  public final EditText editTextInicio;

  @NonNull
  public final EditText editTextNome;

  private ActivityUpdateMedicamentoBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button buttonCor, @NonNull Button buttonDelete, @NonNull Button buttonUpdate,
      @NonNull EditText editTextDesc, @NonNull EditText editTextDose,
      @NonNull EditText editTextHoras, @NonNull EditText editTextInicio,
      @NonNull EditText editTextNome) {
    this.rootView = rootView;
    this.buttonCor = buttonCor;
    this.buttonDelete = buttonDelete;
    this.buttonUpdate = buttonUpdate;
    this.editTextDesc = editTextDesc;
    this.editTextDose = editTextDose;
    this.editTextHoras = editTextHoras;
    this.editTextInicio = editTextInicio;
    this.editTextNome = editTextNome;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityUpdateMedicamentoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityUpdateMedicamentoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_update_medicamento, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityUpdateMedicamentoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonCor;
      Button buttonCor = ViewBindings.findChildViewById(rootView, id);
      if (buttonCor == null) {
        break missingId;
      }

      id = R.id.buttonDelete;
      Button buttonDelete = ViewBindings.findChildViewById(rootView, id);
      if (buttonDelete == null) {
        break missingId;
      }

      id = R.id.buttonUpdate;
      Button buttonUpdate = ViewBindings.findChildViewById(rootView, id);
      if (buttonUpdate == null) {
        break missingId;
      }

      id = R.id.editTextDesc;
      EditText editTextDesc = ViewBindings.findChildViewById(rootView, id);
      if (editTextDesc == null) {
        break missingId;
      }

      id = R.id.editTextDose;
      EditText editTextDose = ViewBindings.findChildViewById(rootView, id);
      if (editTextDose == null) {
        break missingId;
      }

      id = R.id.editTextHoras;
      EditText editTextHoras = ViewBindings.findChildViewById(rootView, id);
      if (editTextHoras == null) {
        break missingId;
      }

      id = R.id.editTextInicio;
      EditText editTextInicio = ViewBindings.findChildViewById(rootView, id);
      if (editTextInicio == null) {
        break missingId;
      }

      id = R.id.editTextNome;
      EditText editTextNome = ViewBindings.findChildViewById(rootView, id);
      if (editTextNome == null) {
        break missingId;
      }

      return new ActivityUpdateMedicamentoBinding((ConstraintLayout) rootView, buttonCor,
          buttonDelete, buttonUpdate, editTextDesc, editTextDose, editTextHoras, editTextInicio,
          editTextNome);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
