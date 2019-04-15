package lkw1120.com.simpletracker.Task;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import lkw1120.com.simpletracker.Model.BlockInfo;
import lkw1120.com.simpletracker.Model.ResultInfo;
import lkw1120.com.simpletracker.Model.TransactionInfo;

public class GetResultTask  extends AsyncTask<String, Integer, ResultInfo> {

    private final String API_URL = "https://bicon.net.solidwallet.io/api/v3";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ResultInfo doInBackground(String... values) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            JSONObject requestJSON = new JSONObject();
            requestJSON.put("jsonrpc","2.0");
            requestJSON.put("method","icx_getTransactionResult");
            requestJSON.put("id","1000");
            JSONObject obj = new JSONObject();
            obj.put("txHash", values[0]);
            requestJSON.put("params", obj);

            OutputStream os = conn.getOutputStream();
            os.write(requestJSON.toString().getBytes());
            os.flush();
            os.close();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] byteBuffer = new byte[1024];
                int length = 0;
                while ((length = is.read(byteBuffer, 0, byteBuffer.length)) != -1) {
                    baos.write(byteBuffer, 0, length);
                }
                byte[] byteData = baos.toByteArray();
                String response = new String(byteData);

                Log.d("RESULT JSON", response);

                JSONObject responseJSON = new JSONObject(response);
                JSONObject resultObject = responseJSON.getJSONObject("result");

                resultInfo.setStatus(resultObject.getString("status"));
                resultInfo.setTo(resultObject.getString("to"));
                resultInfo.setTxHash(resultObject.getString("txHash"));
                resultInfo.setTxIndex(resultObject.getString("txIndex"));
                resultInfo.setBlockHeight(resultObject.getString("blockHeight"));
                resultInfo.setBlockHash(resultObject.getString("blockHash"));
                resultInfo.setCumulativeStepUsed(resultObject.getString("cumulativeStepUsed"));
                resultInfo.setStepUsed(resultObject.getString("stepUsed"));
                resultInfo.setStepPrice(resultObject.getString("stepPrice"));
                resultInfo.setScoreAddress(resultObject.getString("scoreAddress"));
                resultInfo.setEventLogs(resultObject.getString("eventLogs"));
                resultInfo.setLogsBloom(resultObject.getString("logsBloom"));
            }
            conn.disconnect();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return resultInfo;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ResultInfo result) {
        super.onPostExecute(result);
    }
}