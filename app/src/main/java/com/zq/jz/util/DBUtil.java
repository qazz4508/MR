package com.zq.jz.util;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DBUtil {
    public static void copyDbFile(Context context, String db_name, OnCopyListener listener) {
        String path = "/data/data/" + context.getPackageName() + "/databases/";
        File file = new File(path + db_name);
        //创建文件夹
        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        if (file.exists()) {
            listener.onComplete();
            return;
        }

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                InputStream in = null;
                FileOutputStream out = null;
                try {
                    // 从assets目录下复制
                    in = context.getAssets().open(db_name);
                    out = new FileOutputStream(file);
                    int length = -1;
                    byte[] buf = new byte[1024];
                    while ((length = in.read(buf)) != -1) {
                        out.write(buf, 0, length);
                    }
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                    emitter.onError(e);
                } finally {
                    try {
                        if (in != null) in.close();
                        if (out != null) out.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                emitter.onNext("succ");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        listener.onComplete();
                    }
                });
    }

    public interface OnCopyListener {
        void onComplete();
    }
}
