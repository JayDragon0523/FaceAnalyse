package com.smart.post_and_request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smart.key.Key;
import com.smart.pojo.*;

import javax.annotation.Resource;
import javax.net.ssl.SSLException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class UseAPI {
    @Resource
    BodyAnalysisBean bodyAnalisis = new BodyAnalysisBean();
    @Resource
    Face_parts faceParts = new Face_parts();


    public void UseFaceAPI(){
        File file = new File("C:\\Users\\JayDragon\\Desktop\\timg.jpg");
        byte[] buff = getBytesFromFile(file);
        String url = api.face_detection_url;
        HashMap<String, String> map = new HashMap<String, String>();
        HashMap<String, byte[]> byteMap = new HashMap<String, byte[]>();
        map.put("api_key", Key.key);
        map.put("api_secret", Key.Secret);
        map.put("return_landmark", "0");
        map.put("return_attributes", "headpose,eyestatus");
        byteMap.put("image_file", buff);
        try{
            byte[] bacd = post(url, map, byteMap);
            String str = new String(bacd);
            JSONObject jsonObject = JSON.parseObject(str);
            JSONArray faces = jsonObject.getJSONArray("faces");
            int size = faces.size();
            faceParts.setPerson_num(size);
            List<Eyestatus> le = new ArrayList<Eyestatus>();
            List<Eyestatus> re = new ArrayList<Eyestatus>();
            for (int i = 0; i < size; i++){
                Eyestatus ls = new Eyestatus();
                Eyestatus rs = new Eyestatus();
                JSONObject jsonOb = faces.getJSONObject(i);
                JSONObject details = JSON.parseObject(jsonOb.getString("attributes"));
                JSONObject eyestatus = JSON.parseObject(details.getString("eyestatus"));
                JSONObject left_eye_status = JSON.parseObject(eyestatus.getString("left_eye_status"));
                JSONObject right_eye_status = JSON.parseObject(eyestatus.getString("right_eye_status"));
                ls.setOcclusion(Double.valueOf(left_eye_status.getString("occlusion")));
                ls.setDark_glasses(Double.valueOf(left_eye_status.getString("dark_glasses")));
                ls.setNo_glass_eye_open(Double.valueOf(left_eye_status.getString("no_glass_eye_open")));
                ls.setNo_glass_eye_close(Double.valueOf(left_eye_status.getString("no_glass_eye_close")));
                ls.setNormal_glass_eye_open(Double.valueOf(left_eye_status.getString("normal_glass_eye_open")));
                ls.setNormal_glass_eye_close(Double.valueOf(left_eye_status.getString("normal_glass_eye_close")));
                rs.setOcclusion(Double.valueOf(right_eye_status.getString("occlusion")));
                rs.setDark_glasses(Double.valueOf(right_eye_status.getString("dark_glasses")));
                rs.setNo_glass_eye_open(Double.valueOf(right_eye_status.getString("no_glass_eye_open")));
                rs.setNo_glass_eye_close(Double.valueOf(right_eye_status.getString("no_glass_eye_close")));
                rs.setNormal_glass_eye_open(Double.valueOf(right_eye_status.getString("normal_glass_eye_open")));
                rs.setNormal_glass_eye_close(Double.valueOf(right_eye_status.getString("normal_glass_eye_close")));
                le.add(ls);
                re.add(rs);
                //System.out.println(eye.getString());
            }
            faceParts.setLeft_eyestatus(le);
            faceParts.setRight_eyestatus(re);
            System.out.println(str);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void UseBodyAPI(){
        File file = new File("C:\\Users\\JayDragon\\Desktop\\body.jpg");
        byte[] buff = getBytesFromFile(file);
        String url = api.skeleton_detect_url;
        HashMap<String, String> map = new HashMap<String, String>();
        HashMap<String, byte[]> byteMap = new HashMap<String, byte[]>();
        map.put("api_key", Key.key);
        map.put("api_secret", Key.Secret);
        byteMap.put("image_file", buff);
        try{
            byte[] bacd = post(url, map, byteMap);
            String str = new String(bacd);
            JSONObject jsonObject = JSON.parseObject(str);
            JSONArray bodys = jsonObject.getJSONArray("skeletons");
            int size = bodys.size();
            List<Person_info> personInfos = new ArrayList<Person_info>();
            bodyAnalisis.setPerson_num(size);
            for (int i = 0; i < size; i++){
                Person_info p = new Person_info();
                Location l = new Location();
                Body_parts b = new Body_parts();
                Body_parts.Head h = new Body_parts.Head();Body_parts.Neck n = new Body_parts.Neck();Body_parts.Left_shoulder ls = new Body_parts.Left_shoulder();Body_parts.Right_shoulder rs = new Body_parts.Right_shoulder();
                Body_parts.Left_buttocks lb = new Body_parts.Left_buttocks();Body_parts.Right_buttocks rb = new Body_parts.Right_buttocks();Body_parts.Left_elbow le = new Body_parts.Left_elbow();Body_parts.Right_elbow re = new Body_parts.Right_elbow();
                Body_parts.Left_foot lf = new Body_parts.Left_foot();Body_parts.Right_foot rf = new Body_parts.Right_foot();Body_parts.Left_hand lh = new Body_parts.Left_hand();Body_parts.Right_hand rh = new Body_parts.Right_hand();
                Body_parts.Left_knee lk = new Body_parts.Left_knee();Body_parts.Right_knee rk = new Body_parts.Right_knee();
                JSONObject jsonOb = bodys.getJSONObject(i);
                JSONObject rectangle = JSON.parseObject(jsonOb.getString("body_rectangle"));
                JSONObject landmark = JSON.parseObject(jsonOb.getString("landmark"));
                int width = Integer.parseInt(rectangle.getString("width"));
                int top = Integer.parseInt(rectangle.getString("top"));
                int height = Integer.parseInt(rectangle.getString("height"));
                int left = Integer.parseInt(rectangle.getString("left"));
                l.setHeight(height);l.setLeft(left);l.setTop(top);l.setWidth(width);
                //System.out.println(Double.valueOf(JSON.parseObject(landmark.getString("head")).getString("x")));
                h.setX(Double.valueOf(JSON.parseObject(landmark.getString("head")).getString("x")));h.setY(Double.valueOf(JSON.parseObject(landmark.getString("head")).getString("y")));
                n.setX(Double.valueOf(JSON.parseObject(landmark.getString("neck")).getString("x")));n.setY(Double.valueOf(JSON.parseObject(landmark.getString("neck")).getString("y")));
                ls.setX(Double.valueOf(JSON.parseObject(landmark.getString("left_shoulder")).getString("x")));ls.setY(Double.valueOf(JSON.parseObject(landmark.getString("left_shoulder")).getString("y")));
                rs.setX(Double.valueOf(JSON.parseObject(landmark.getString("right_shoulder")).getString("x")));rs.setY(Double.valueOf(JSON.parseObject(landmark.getString("right_shoulder")).getString("y")));
                lb.setX(Double.valueOf(JSON.parseObject(landmark.getString("left_buttocks")).getString("x")));lb.setY(Double.valueOf(JSON.parseObject(landmark.getString("left_buttocks")).getString("y")));
                rb.setX(Double.valueOf(JSON.parseObject(landmark.getString("right_buttocks")).getString("x")));rb.setY(Double.valueOf(JSON.parseObject(landmark.getString("right_buttocks")).getString("y")));
                le.setX(Double.valueOf(JSON.parseObject(landmark.getString("left_elbow")).getString("x")));le.setY(Double.valueOf(JSON.parseObject(landmark.getString("left_elbow")).getString("y")));
                re.setX(Double.valueOf(JSON.parseObject(landmark.getString("right_elbow")).getString("x")));re.setY(Double.valueOf(JSON.parseObject(landmark.getString("right_elbow")).getString("y")));
                lf.setX(Double.valueOf(JSON.parseObject(landmark.getString("left_foot")).getString("x")));lf.setY(Double.valueOf(JSON.parseObject(landmark.getString("left_foot")).getString("y")));
                rf.setX(Double.valueOf(JSON.parseObject(landmark.getString("right_foot")).getString("x")));rf.setY(Double.valueOf(JSON.parseObject(landmark.getString("right_foot")).getString("y")));
                lh.setX(Double.valueOf(JSON.parseObject(landmark.getString("left_hand")).getString("x")));lh.setY(Double.valueOf(JSON.parseObject(landmark.getString("left_hand")).getString("y")));
                rh.setX(Double.valueOf(JSON.parseObject(landmark.getString("right_hand")).getString("x")));rh.setY(Double.valueOf(JSON.parseObject(landmark.getString("right_hand")).getString("y")));
                lk.setX(Double.valueOf(JSON.parseObject(landmark.getString("left_knee")).getString("x")));lk.setY(Double.valueOf(JSON.parseObject(landmark.getString("left_knee")).getString("y")));
                rk.setX(Double.valueOf(JSON.parseObject(landmark.getString("right_knee")).getString("x")));rk.setY(Double.valueOf(JSON.parseObject(landmark.getString("right_knee")).getString("y")));

                b.setHead(h);b.setLeft_buttocks(lb);b.setRight_buttocks(rb);b.setLeft_elbow(le);b.setRight_elbow(re);
                b.setNeck(n);b.setLeft_foot(lf);b.setRight_foot(rf);b.setLeft_hand(lh);b.setRight_hand(rh);
                b.setLeft_knee(lk);b.setRight_knee(rk);b.setLeft_shoulder(ls);b.setRight_shoulder(rs);
                p.setBody_parts(b);p.setLocation(l);
                personInfos.add(p);
//                System.out.println(rectangle.getString("width"));
//                System.out.println(landmark);
            }
            bodyAnalisis.setPerson_info(personInfos);
            System.out.println(str);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean sleep(JSONObject jsonObject){
        return true;
    }

    public boolean badHeadpose(JSONObject jsonObject){
        return true;
    }

    public boolean playPhone(JSONObject jsonObject){
        return true;
    }

    private final static int CONNECT_TIME_OUT = 30000;
    private final static int READ_OUT_TIME = 50000;
    private static String boundaryString = getBoundary();
    protected static byte[] post(String url, HashMap<String, String> map, HashMap<String, byte[]> fileMap) throws Exception {
        HttpURLConnection conne;
        URL url1 = new URL(url);
        conne = (HttpURLConnection) url1.openConnection();
        conne.setDoOutput(true);
        conne.setUseCaches(false);
        conne.setRequestMethod("POST");
        conne.setConnectTimeout(CONNECT_TIME_OUT);
        conne.setReadTimeout(READ_OUT_TIME);
        conne.setRequestProperty("accept", "*/*");
        conne.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
        conne.setRequestProperty("connection", "Keep-Alive");
        conne.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
        DataOutputStream obos = new DataOutputStream(conne.getOutputStream());
        Iterator iter = map.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<String, String> entry = (Map.Entry) iter.next();
            String key = entry.getKey();
            String value = entry.getValue();
            obos.writeBytes("--" + boundaryString + "\r\n");
            obos.writeBytes("Content-Disposition: form-data; name=\"" + key
                    + "\"\r\n");
            obos.writeBytes("\r\n");
            obos.writeBytes(value + "\r\n");
        }
        if(fileMap != null && fileMap.size() > 0){
            Iterator fileIter = fileMap.entrySet().iterator();
            while(fileIter.hasNext()){
                Map.Entry<String, byte[]> fileEntry = (Map.Entry<String, byte[]>) fileIter.next();
                obos.writeBytes("--" + boundaryString + "\r\n");
                obos.writeBytes("Content-Disposition: form-data; name=\"" + fileEntry.getKey()
                        + "\"; filename=\"" + encode(" ") + "\"\r\n");
                obos.writeBytes("\r\n");
                obos.write(fileEntry.getValue());
                obos.writeBytes("\r\n");
            }
        }
        obos.writeBytes("--" + boundaryString + "--" + "\r\n");
        obos.writeBytes("\r\n");
        obos.flush();
        obos.close();
        InputStream ins = null;
        int code = conne.getResponseCode();
        try{
            if(code == 200){
                ins = conne.getInputStream();
            }else{
                ins = conne.getErrorStream();
            }
        }catch (SSLException e){
            e.printStackTrace();
            return new byte[0];
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[4096];
        int len;
        while((len = ins.read(buff)) != -1){
            baos.write(buff, 0, len);
        }
        byte[] bytes = baos.toByteArray();
        ins.close();
        return bytes;
    }
    private static String getBoundary() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 32; ++i) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".length())));
        }
        return sb.toString();
    }
    private static String encode(String value) throws Exception{
        return URLEncoder.encode(value, "UTF-8");
    }

    public static byte[] getBytesFromFile(File f) {
        if (f == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = stream.read(b)) != -1)
                out.write(b, 0, n);
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
        }
        return null;
    }
}
