package cn.edu.nuc.asynctaskdownloaddemo_94240_;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DownloadTask extends AsyncTask{

    private TextView tvFinish = null;
    private ProgressBar bar = null;

    public DownloadTask(){

    }

    public DownloadTask(TextView tvFinish, ProgressBar bar) {
        this.tvFinish = tvFinish;
        this.bar = bar;
    }

    @Override
    protected String doInBackground(Object[] objects) {

        for(int i = 0; i < 101; i++)
            try {
                publishProgress(i);
                Thread.sleep((int) objects[0]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        return "下载安装完毕！";
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
        bar.setProgress((int) values[0]);
        bar.setVisibility(View.VISIBLE);
        tvFinish.setText("当前完成任务的：" + (int)values[0] + "%");
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        tvFinish.setText((String)o);
        bar.setVisibility(View.INVISIBLE);
    }
}
