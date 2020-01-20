package com.little.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.little.App;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.datatype.Artwork;

/**
 * @author created by qingchuan.xia
 */
abstract class BaseUnpackNcm {

    RepeatReadStream stream = new RepeatReadStream();

    void ncm2NormalFormat(File file) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException, CannotWriteException {

        try {
            //文件头有8位header 2位空byte
            stream.setFile(file);
            stream.skip(10);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } finally {
            if (stream == null) {
                stream.close();
            }
        }

        //音乐段byte的密码
        byte[] keyData = readKey();
        //音乐文件的mete字符串
        String meteData = readMetaData();
        int crc32 = readCrc32();
        byte[] cover = readAlbumCover();
        byte[] musicData = readMusic(keyData);
        MusicInfo musicInfo = getInfo(file.getParent() + App.SAVE_FILE_PATH, meteData);
        File saveFile = new File(musicInfo.getFilePath());
        if (saveFile.exists()) {
            saveFile = new File(musicInfo.getFilePath() + "_" + System.currentTimeMillis());
        }
        write(saveFile, musicData);
        AudioFile music = AudioFileIO.read(saveFile);
        Tag tag = music.getTag();
        tag.deleteArtworkField();
        setMusicTag(tag, musicInfo);
        Artwork artwork = new Artwork();
        setCover(artwork, cover);
        tag.setField(artwork);
        music.commit();
    }

    /**
     * 读取解密秘钥信息
     */
    abstract byte[] readKey() throws IOException;

    abstract String readMetaData() throws IOException;

    abstract int readCrc32() throws IOException;

    abstract byte[] readAlbumCover() throws IOException;

    abstract byte[] readMusic(byte[] keyByte);

    abstract MusicInfo getInfo(String parentPath, String meteData);

    abstract void write(File target, byte[] data);

    abstract void setMusicTag(Tag tag, MusicInfo musicInfo) throws FieldDataInvalidException;

    private void setCover(Artwork artwork, byte[] cover) {
        artwork.setBinaryData(cover);
        artwork.setMimeType("image/jpeg");
        artwork.setPictureType(3);
    }


}
