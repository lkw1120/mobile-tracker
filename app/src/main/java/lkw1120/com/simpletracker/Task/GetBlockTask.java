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

public class GetBlockTask  extends AsyncTask<Integer, String, BlockInfo> {

    private final String API_URL = "https://bicon.net.solidwallet.io/api/v3";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected BlockInfo doInBackground(Integer... values) {
        BlockInfo blockInfo = new BlockInfo();
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            JSONObject requestJSON = new JSONObject();
            requestJSON.put("jsonrpc","2.0");
            requestJSON.put("id","1000");
            if(values[0] < 0) {
                requestJSON.put("method","icx_getLastBlock");
            }
            else {
                requestJSON.put("method", "icx_getBlockByHeight");
                JSONObject obj = new JSONObject();
                obj.put("height", "0x" + Integer.toHexString(values[0]));
                requestJSON.put("params", obj);
            }

            OutputStream os = conn.getOutputStream();
            os.write(requestJSON.toString().getBytes());
            os.flush();
            os.close();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] byteBuffer = new byte[1024];
                int length;
                while ((length = is.read(byteBuffer, 0, byteBuffer.length)) != -1) {
                    baos.write(byteBuffer, 0, length);
                }
                byte[] byteData = baos.toByteArray();
                String response = new String(byteData);

                JSONObject responseJSON = new JSONObject(response);
                JSONObject resultObject = responseJSON.getJSONObject("result");
                JSONArray txArray = resultObject.getJSONArray("confirmed_transaction_list");

                ArrayList<String> txHash = new ArrayList<>();
                for (int i = 0; i < txArray.length(); i++) {
                    txHash.add(txArray.getJSONObject(i).getString("txHash"));
                    //Log.d("txHash value",txArray.getJSONObject(i).getString("txHash"));
                }
                blockInfo.setVersion(resultObject.getString("version"));
                blockInfo.setPrevBlockHash(resultObject.getString("prev_block_hash"));
                blockInfo.setMerkleTreeRootHash(resultObject.getString("merkle_tree_root_hash"));
                blockInfo.setTxHash(txHash);
                blockInfo.setBlockHash(resultObject.getString("block_hash"));
                blockInfo.setHeight(resultObject.getString("height"));
                blockInfo.setPeerId(resultObject.getString("peer_id"));
                blockInfo.setSignature(resultObject.getString("signature"));
                blockInfo.setSelected(false);

                conn.disconnect();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return blockInfo;
    }

    @Override
    protected void onPostExecute(BlockInfo result) {
        super.onPostExecute(result);
    }
}