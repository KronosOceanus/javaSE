package ctrl;

/**
 * 状态字符串
 */
public interface TaskState {

    String CONNECTING = "connecting";
    String DOWNLOADING = "downloading";
    String FAILED = "failed";
    String FINISHED = "finished";
    String PAUSE = "pause";

}
