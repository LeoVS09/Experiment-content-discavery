package cd.ex.leovs09.experiment_content_discavery;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.VideoView;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    ToggleButton enableMediaController;
    VideoView myVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.hide();
//        enableMediaController = (ToggleButton) findViewById(R.id.enableMediaController);
        myVideoView = (VideoView) findViewById(R.id.myvideoview);
//        myVideoView.setVideoPath(getViewSrc());
        myVideoView.setVideoURI(Uri.parse(""));
        myVideoView.requestFocus();
        myVideoView.start();

//        setMediaController();

//        enableMediaController.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                setMediaController();
//            }
//        });
    }

    private void setMediaController(){
        if(enableMediaController.isChecked()){
            myVideoView.setMediaController(new MediaController(this));
        }else{
            myVideoView.setMediaController(null);
        }
    }
    private String getViewSrc(){
        File extStorageDirectory = Environment.getExternalStorageDirectory();
        String s = extStorageDirectory.getAbsolutePath();
        s += "/test.mp4";
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
        return s;
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

}
