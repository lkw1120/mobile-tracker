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
import lkw1120.com.simpletracker.Model.TransactionInfo;

public class GetTransactionTask  extends AsyncTask<String, Integer, ArrayList<TransactionInfo>> {

    private final String API_URL = "https://bicon.net.solidwallet.io/api/v3";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<TransactionInfo> doInBackground(String... values) {
        ArrayList<TransactionInfo> txList = new ArrayList<>();
        try {
            for(int i=0;i<values.length;i++) {
                URL url = new URL(API_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type","application/json");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                JSONObject requestJSON = new JSONObject();
                requestJSON.put("jsonrpc","2.0");
                requestJSON.put("method","icx_getTransactionByHash");
                requestJSON.put("id","1000");
                JSONObject obj = new JSONObject();
                obj.put("txHash", values[i]);
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

                    Log.d("TRANSACTION JSON", response);

                    JSONObject responseJSON = new JSONObject(response);
                    JSONObject resultObject = responseJSON.getJSONObject("result");

                    TransactionInfo txInfo = new TransactionInfo();
                    txInfo.setVersion(resultObject.getString("version"));
                    txInfo.setFrom(resultObject.getString("from"));
                    txInfo.setTo(resultObject.getString("to"));
                    txInfo.setStepLimit(resultObject.getString("stepLimit"));
                    txInfo.setTimestamp(resultObject.getString("timestamp"));
                    txInfo.setNid(resultObject.getString("nid"));
                    txInfo.setNonce(resultObject.getString("nonce"));
                    txInfo.setTxHash(resultObject.getString("txHash"));
                    txInfo.setTxIndex(resultObject.getString("txIndex"));
                    txInfo.setBlockHeight(resultObject.getString("blockHeight"));
                    txInfo.setBlockHash(resultObject.getString("blockHash"));
                    txInfo.setSignature(resultObject.getString("signature"));

                    txList.add(txInfo);
                    publishProgress(i);
                }
                conn.disconnect();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return txList;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ArrayList<TransactionInfo> result) {
        super.onPostExecute(result);
    }
}