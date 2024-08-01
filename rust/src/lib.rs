use jni::JNIEnv;
use jni::objects::JClass;
use jni::sys::{jbyteArray, jstring};
use windows::Media::Control::{GlobalSystemMediaTransportControlsSession, GlobalSystemMediaTransportControlsSessionManager, GlobalSystemMediaTransportControlsSessionPlaybackStatus};
use windows::Storage::Streams::{Buffer, DataReader, InputStreamOptions};

#[no_mangle]
pub extern "system" fn Java_com_minearchive_WinAPI_tryStart(_env: JNIEnv, _class: JClass) {
    let _ = get_session().TryPlayAsync();
}

#[no_mangle]
pub extern "system" fn Java_com_minearchive_WinAPI_tryStop(_env: JNIEnv, _class: JClass) {
    let _ = get_session().TryStopAsync();
}

#[no_mangle]
pub extern "system" fn Java_com_minearchive_WinAPI_trySkipNext(_env: JNIEnv, _class: JClass) {
    let _ = get_session().TrySkipNextAsync();
}

#[no_mangle]
pub extern "system" fn Java_com_minearchive_WinAPI_trySkipPrevious(_env: JNIEnv, _class: JClass) {
    let _ = get_session().TrySkipNextAsync();
}

#[no_mangle]
pub extern "system" fn Java_com_minearchive_WinAPI_tryGetCurrentPlaying(_env: JNIEnv, _class: JClass) -> jstring {
    let mut s_str = String::new();
    let props = get_session().TryGetMediaPropertiesAsync().unwrap().get().unwrap();

    s_str.push_str(&props.Title().unwrap().to_string());
    s_str.push_str(",");
    s_str.push_str(&props.Artist().unwrap().to_string());
    s_str.push_str(",");
    s_str.push_str(&props.AlbumTitle().unwrap().to_string());
    s_str.push_str(",");
    s_str.push_str(&props.AlbumArtist().unwrap().to_string());
    s_str.push_str(",");
    s_str.push_str(&props.AlbumTrackCount().unwrap().to_string());
    s_str.push_str(",");
    s_str.push_str(&props.TrackNumber().unwrap().to_string());

    return _env.new_string(s_str).unwrap().into_raw()
}

#[no_mangle]
pub extern "system" fn Java_com_minearchive_WinAPI_tryGetState(_env: JNIEnv, _class: JClass) -> jstring {
    let mut s_str = String::new();
    let stats = get_session().GetPlaybackInfo().unwrap().PlaybackStatus().unwrap();
    match stats {
        GlobalSystemMediaTransportControlsSessionPlaybackStatus::Changing => s_str.push_str("changing"),
        GlobalSystemMediaTransportControlsSessionPlaybackStatus::Playing => s_str.push_str("playing"),
        GlobalSystemMediaTransportControlsSessionPlaybackStatus::Paused => s_str.push_str("paused"),
        GlobalSystemMediaTransportControlsSessionPlaybackStatus::Stopped => s_str.push_str("stopped"),
        _ => (),  // Handle any other cases if necessary
    }
    s_str.push_str(",");

    if get_session().GetPlaybackInfo().unwrap().Controls().unwrap().IsPlayEnabled().unwrap() { s_str.push_str("y") } else { s_str.push_str("n") } s_str.push_str(",");
    if get_session().GetPlaybackInfo().unwrap().Controls().unwrap().IsPauseEnabled().unwrap() { s_str.push_str("y") } else { s_str.push_str("n") } s_str.push_str(",");
    if get_session().GetPlaybackInfo().unwrap().Controls().unwrap().IsStopEnabled().unwrap() { s_str.push_str("y") } else { s_str.push_str("n") } s_str.push_str(",");
    if get_session().GetPlaybackInfo().unwrap().Controls().unwrap().IsRecordEnabled().unwrap() { s_str.push_str("y") } else { s_str.push_str("n") } s_str.push_str(",");
    if get_session().GetPlaybackInfo().unwrap().Controls().unwrap().IsFastForwardEnabled().unwrap() { s_str.push_str("y") } else { s_str.push_str("n") } s_str.push_str(",");
    if get_session().GetPlaybackInfo().unwrap().Controls().unwrap().IsRewindEnabled().unwrap() { s_str.push_str("y") } else { s_str.push_str("n") } s_str.push_str(",");
    if get_session().GetPlaybackInfo().unwrap().Controls().unwrap().IsNextEnabled().unwrap() { s_str.push_str("y") } else { s_str.push_str("n") } s_str.push_str(",");
    if get_session().GetPlaybackInfo().unwrap().Controls().unwrap().IsPreviousEnabled().unwrap() { s_str.push_str("y") } else { s_str.push_str("n") } s_str.push_str(",");
    if get_session().GetPlaybackInfo().unwrap().Controls().unwrap().IsChannelUpEnabled().unwrap() { s_str.push_str("y") } else { s_str.push_str("n") } s_str.push_str(",");
    if get_session().GetPlaybackInfo().unwrap().Controls().unwrap().IsChannelDownEnabled().unwrap() { s_str.push_str("y") } else { s_str.push_str("n") } s_str.push_str(",");
    if get_session().GetPlaybackInfo().unwrap().Controls().unwrap().IsPlayPauseToggleEnabled().unwrap() { s_str.push_str("y") } else { s_str.push_str("n") } s_str.push_str(",");
    if get_session().GetPlaybackInfo().unwrap().Controls().unwrap().IsShuffleEnabled().unwrap() { s_str.push_str("y") } else { s_str.push_str("n") } s_str.push_str(",");
    if get_session().GetPlaybackInfo().unwrap().Controls().unwrap().IsRepeatEnabled().unwrap() { s_str.push_str("y") } else { s_str.push_str("n") } s_str.push_str(",");
    if get_session().GetPlaybackInfo().unwrap().Controls().unwrap().IsPlaybackRateEnabled().unwrap() { s_str.push_str("y") } else { s_str.push_str("n") } s_str.push_str(",");
    if get_session().GetPlaybackInfo().unwrap().Controls().unwrap().IsPlaybackPositionEnabled().unwrap() { s_str.push_str("y") } else { s_str.push_str("n") } s_str.push_str(",");

    s_str.push_str(get_session().GetTimelineProperties().unwrap().StartTime().unwrap().Duration.to_string().as_str()); s_str.push_str(",");
    s_str.push_str(get_session().GetTimelineProperties().unwrap().EndTime().unwrap().Duration.to_string().as_str()); s_str.push_str(",");
    s_str.push_str(get_session().GetTimelineProperties().unwrap().Position().unwrap().Duration.to_string().as_str()); s_str.push_str(",");
    s_str.push_str(get_session().GetTimelineProperties().unwrap().MinSeekTime().unwrap().Duration.to_string().as_str()); s_str.push_str(",");
    s_str.push_str(get_session().GetTimelineProperties().unwrap().MaxSeekTime().unwrap().Duration.to_string().as_str()); s_str.push_str(",");
    return _env.new_string(s_str).unwrap().into_raw()
}

#[no_mangle]
pub extern "system" fn Java_com_minearchive_WinAPI_tryGetCover(_env: JNIEnv, _class: JClass) -> jbyteArray {
    let stream_ref = get_session().TryGetMediaPropertiesAsync().unwrap().get().unwrap().Thumbnail().unwrap();

    // Step 2: Open the stream and read the data into a buffer
    let stream = stream_ref.OpenReadAsync().unwrap().get().unwrap();
    let size = stream.Size().unwrap() as usize;

    // Create an IBuffer to read the data
    let buffer = Buffer::Create(size as u32).unwrap();
    stream.ReadAsync(&buffer, size as u32, InputStreamOptions::None).unwrap().get().unwrap();

    let data_reader = DataReader::FromBuffer(&buffer).unwrap();
    let mut vec_buffer = vec![0u8; size];
    data_reader.ReadBytes(&mut vec_buffer).unwrap();

    return _env.byte_array_from_slice(&vec_buffer).unwrap().into_raw();
}

fn get_session() -> GlobalSystemMediaTransportControlsSession {
    let manager = GlobalSystemMediaTransportControlsSessionManager::RequestAsync().unwrap();
    return manager.get().unwrap().GetCurrentSession().unwrap();
}
