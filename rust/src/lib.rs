use j4rs::Jvm;
use j4rs::prelude::c_void;
use j4rs::prelude::JNIEnv;
use j4rs_derive::call_from_java;
use windows::Media::Control::{
    GlobalSystemMediaTransportControlsSession, GlobalSystemMediaTransportControlsSessionManager,
};

#[call_from_java("com.minearchive.WinAPI.tryStart")]
fn try_start() {
    let _ = get_session().TryPlayAsync();
}

#[call_from_java("com.minearchive.WinAPI.tryStop")]
fn try_stop() {
    let _ = get_session().TryStopAsync();
}

#[call_from_java("com.minearchive.WinAPI.trySkipNext")]
fn try_skip_next() {
    let _ = get_session().TrySkipNextAsync();
}

#[call_from_java("com.minearchive.WinAPI.trySkipPrevious")]
fn try_skip_previous() {
    let _ = get_session().TrySkipPreviousAsync();
}

fn get_session() -> GlobalSystemMediaTransportControlsSession {
    let manager = GlobalSystemMediaTransportControlsSessionManager::RequestAsync().unwrap();
    return manager.get().unwrap().GetCurrentSession().unwrap();
}
