package com.hsh.dongzi.jinshang.utils;

import android.hardware.Camera;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Build;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * create by liu
 * on2019/7/10
 */
public class VivoPermissionUtil {


    private VivoPermissionUtil() {

    }

    public static VivoPermissionUtil getInstance() {
        return PermissionUtilHolder.sInstance;
    }

    private static class PermissionUtilHolder {
        private static final VivoPermissionUtil sInstance = new VivoPermissionUtil();
    }

    public boolean requestPermission(String... permissions) {
        try {
            ArrayList<String> ps = new ArrayList<>();
            for (String p : permissions) {
                ps.add(p);
            }
            if (ps.contains("android.permission.CAMERA")) {
                if (!isCameraCanUse()) {
                    return false;
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!isHasCameraPermission()) {
                        return false;
                    }
                }
            }
            if (ps.contains("android.permission.RECORD_AUDIO")) {
                if (!isHasRecordPermission()) {
                    return false;
                }
            }

        } catch (Exception ex) {
            return false;
        }
        return true;
    }


    public static boolean isCameraCanUse() {
        boolean canUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
            // setParameters 是针对魅族MX5 做的。MX5 通过Camera.open() 拿到的Camera
            // 对象不为null
            Camera.Parameters mParameters = mCamera.getParameters();
            mCamera.setParameters(mParameters);
        } catch (Exception e) {
            canUse = false;
        }
        if (mCamera != null) {
            mCamera.release();
        }
        return canUse;
    }

    public static boolean isHasCameraPermission() {
        Field fieldPassword = null;
        try {
            Camera camera = Camera.open();
            fieldPassword = camera.getClass().getDeclaredField("mHasPermission");
            fieldPassword.setAccessible(true);
            Boolean result = (Boolean) fieldPassword.get(camera);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    /**
     * 判断是是否有录音权限
     */
    public static boolean isHasRecordPermission() {
        // 音频获取源
        int audioSource = MediaRecorder.AudioSource.MIC;
        // 设置音频采样率，44100是目前的标准，但是某些设备仍然支持22050，16000，11025
        int sampleRateInHz = 44100;
        // 设置音频的录制的声道CHANNEL_IN_STEREO为双声道，CHANNEL_CONFIGURATION_MONO为单声道
        int channelConfig = AudioFormat.CHANNEL_IN_STEREO;
        // 音频数据格式:PCM 16位每个样本。保证设备支持。PCM 8位每个样本。不一定能得到设备支持。
        int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
        // 缓冲区字节大小
        int bufferSizeInBytes = 0;


        bufferSizeInBytes = 0;
        bufferSizeInBytes = AudioRecord.getMinBufferSize(sampleRateInHz,
                channelConfig, audioFormat);
        AudioRecord audioRecord = new AudioRecord(audioSource, sampleRateInHz,
                channelConfig, audioFormat, bufferSizeInBytes);
        //开始录制音频
        try {
            // 防止某些手机崩溃，例如联想
            audioRecord.startRecording();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        /**
         * 根据开始录音判断是否有录音权限
         */
        if (audioRecord.getRecordingState() != AudioRecord.RECORDSTATE_RECORDING) {
            return false;
        }
        audioRecord.stop();
        audioRecord.release();
        audioRecord = null;

        return true;
    }
}
