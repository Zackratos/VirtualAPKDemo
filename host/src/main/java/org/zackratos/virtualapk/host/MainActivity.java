package org.zackratos.virtualapk.host;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.didi.virtualapk.PluginManager;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    private static final String PLUGIN_PKG_1 = "org.zackratos.virtualapk.plugin1";
    private static final String PLUGIN_PKG_2 = "org.zackratos.virtualapk.plugin2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadPlugin("plugin1.apk");
        loadPlugin("plugin2.apk");
    }




    private void loadPlugin(String fileName) {
        PluginManager pluginManager = PluginManager.getInstance(this);
        File apk = new File(getExternalFilesDir("plugin"), fileName);
        if (apk.exists()) {
            try {
                pluginManager.loadPlugin(apk);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public void enterPlugin1(View view) {
        enterPlugin(PLUGIN_PKG_1);
    }

    public void enterPlugin2(View view) {
        enterPlugin(PLUGIN_PKG_2);
    }





    private void enterPlugin(String pkg) {
        if (PluginManager.getInstance(this).getLoadedPlugin(pkg) == null) {
            Toast.makeText(getApplicationContext(),
                    "插件未加载,请尝试重启APP", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(pkg, pkg + ".MainActivity");
        startActivity(intent);
    }




}
