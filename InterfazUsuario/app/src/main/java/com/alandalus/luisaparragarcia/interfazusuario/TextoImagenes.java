package com.alandalus.luisaparragarcia.interfazusuario;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.Timer;
import java.util.TimerTask;

public class TextoImagenes extends AppCompatActivity {
    private ImageSwitcher imageSwitcher;

    private int[] gallery = { R.drawable.tunnel, R.drawable.camera, R.drawable.planet };

    private int position;

    private static final Integer DURATION = 2500;

    private Timer timer = null;

    private EditText txtTexto;
    private Button btnNegrita;
    private Button btnSetText;
    private TextInputLayout txtInputLayout;
    private EditText txtInput;
    private Button btnComprobar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texto_imagenes);
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                return new ImageView(TextoImagenes.this);
            }
        });
        Animation fadeIn = AnimationUtils.loadAnimation(this,android.R.anim.fade_in );
        Animation fadeOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        imageSwitcher.setInAnimation(fadeIn);
        imageSwitcher.setOutAnimation(fadeOut);

        // Sapanable
        txtTexto = (EditText)findViewById(R.id.TxtBasico);

        btnNegrita = (Button)findViewById(R.id.BtnNegrita);
        btnNegrita.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                Spannable texto = txtTexto.getText();

                int ini = txtTexto.getSelectionStart();
                int fin = txtTexto.getSelectionEnd();

                if(texto != null) {
                    texto.setSpan(
                            new StyleSpan(android.graphics.Typeface.BOLD),
                            ini, fin,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        });

        btnSetText = (Button)findViewById(R.id.BtnSetText);

        btnSetText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                Editable str = Editable.Factory.getInstance().newEditable("Esto es un simulacro.");
                str.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 11, 20, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                txtTexto.setText(str);

                //Nueva cadena de texto
                //String nuevoTexto = "<p>Otro <b>texto</b> de ejemplo.</p>";

                //Asigna texto sin formato (incluirá todas las etiquetas HTML)
                //txtTexto.setText(nuevoTexto);

                //Asigna texto con formato HTML
                //txtTexto.setText(Html.fromHtml(nuevoTexto),BufferType.SPANNABLE);

                //Obtiene el texto SIN etiquetas de formato HTML
                //String aux1 = txtTexto.getText().toString();

                //Obtiene el texto CON etiquetas de formato HTML
                //String aux2 = Html.toHtml(txtTexto.getText());
            }
        });

        //TextInputLayout
        txtInputLayout = (TextInputLayout)findViewById(R.id.TiLayout);
        txtInputLayout.setErrorEnabled(true);

        txtInput = (EditText)findViewById(R.id.TxtInput);

        btnComprobar = (Button)findViewById(R.id.BtnInputLayout);
        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = txtInput.getText().toString();

                if(num.isEmpty() || Integer.parseInt(num)%2 != 0)
                    txtInputLayout.setError("Error: No es un número par!");
                else
                    txtInputLayout.setError(null);
            }
        });
    }
    /**
     * starts or restarts the slider
     *
     * @param button
     */
    public void start(View button) {
        if (timer != null) {
            timer.cancel();
        }
        position = 0;
        startSlider();
    }

    public void stop(View button) {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void startSlider() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                // avoid exception:
                // "Only the original thread that created a view hierarchy can touch its views"
                runOnUiThread(new Runnable() {
                    public void run() {
                        imageSwitcher.setImageResource(gallery[position]);
                        position++;
                        if (position == gallery.length) {
                            position = 0;
                        }
                    }
                });
            }

        }, 0, DURATION);
    }

    // Stops the slider when the Activity is going into the background
    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timer != null) {
            startSlider();
        }

    }
}
