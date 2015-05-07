package org.bbs.android.floatwindow;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // use appliction context
        new AlwaysOnTopWindow(getApplicationContext()).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class AlwaysOnTopWindow {
        private final Context mContext;

        public AlwaysOnTopWindow(Context context){
            mContext = context;
        }

        public void show(){
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            Button content = new Button(mContext);
            content.setText("Click me !!! ");
            content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "hello, AlwaysOnTopWindow.", Toast.LENGTH_SHORT).show();
                }
            });

            //http://stackoverflow.com/questions/4481226/creating-a-system-overlay-window-always-on-top
            WindowManager.LayoutParams p = new WindowManager.LayoutParams();
            p.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
            p.x = 20;
            p.y = 50;
            p.width = 200;
            p.height = 100;
            p.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            wm.addView(content, p);
        }
    }
}
