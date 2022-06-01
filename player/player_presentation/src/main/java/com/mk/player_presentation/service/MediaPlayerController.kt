package com.mk.player_presentation.service

import android.media.MediaPlayer
import com.mk.player_domain.model.Song
import javax.inject.Inject

class MediaPlayerController @Inject constructor(
    var mediaPlayer: MediaPlayer
) {
    fun handlePlayPause() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        } else {
            mediaPlayer.start()
        }
    }

    fun reset(datasource: String, completionListener: MediaPlayer.OnCompletionListener, preparedListener: MediaPlayer.OnPreparedListener) {
        mediaPlayer.apply {
            reset()
            setOnCompletionListener(completionListener)
            setOnPreparedListener(preparedListener)
            setDataSource(datasource)
            prepareAsync()
        }
    }

    //TODO: Remove MusicService's State from this class!
    //TODO: Review nextSong and previousSong logic. See how we can reuse logic
    fun nextSong(): Song? {
        val trackList = MusicService.state.trackList
        val currentIndex = trackList.indexOf(MusicService.state.currentSong)
        if (currentIndex + 1 >= trackList.size) {
            if (MusicService.state.repeatState == LoopState.TRACKS && trackList.isNotEmpty()) {
                return trackList.first()
            } else if (MusicService.state.repeatState == LoopState.SONG) {
                return MusicService.state.currentSong
            }
        } else {
            return trackList[currentIndex + 1]
        }
        return null
    }

    fun previousSong(): Song? {
        val trackList = MusicService.state.trackList
        val currentIndex = trackList.indexOf(MusicService.state.currentSong)
        if (currentIndex - 1 < 0) {
            if (MusicService.state.repeatState == LoopState.TRACKS && trackList.isNotEmpty()) {
                return trackList.last()
            } else if (MusicService.state.repeatState == LoopState.SONG) {
                return MusicService.state.currentSong
            }
        } else {
            return trackList[currentIndex - 1]
        }
        return null
    }

    fun release() {
        mediaPlayer.release()
    }

    fun start() {
        mediaPlayer.start()
    }

    fun isPlaying() = mediaPlayer.isPlaying

    fun currentTime() = mediaPlayer.currentPosition / 1000

}