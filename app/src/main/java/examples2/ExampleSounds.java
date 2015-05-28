package examples2;

/**
 * Created by herpderp on 27/05/2015.
 *
 * The sounds and music. We've created a folder in assets /sfx for these resources,
 * and the most standard format to use is .mp3
 *
 * The Sound objects are supposed for collisions and other small sounds, while the Music
 * objects are suppose to be long playback events.
 *
 * Setting up sounds/music
 * step1:
 * The first thing  we have to do to enable these features is to call the
 * engineOptions.getAudioOptions().setNeedsMusic(true);
 * engineOptions.getAudioOptions().setNeedsSound(true);
 *
 *  in our onCreateEngineOptions method.
 *
 * step2:
 * in our onCreateResources() method we should set the baseAssetsPath.
 *  Set the base path for our SoundFactory and MusicFactory to *
 * define where they will look for audio files.
    SoundFactory.setAssetBasePath("sfx/");
    MusicFactory.setAssetBasePath("sfx/"); // Load our "sound.mp3" file into a Sound objecttry
 {
    Sound mSound = SoundFactory.createSoundFromAsset(getSoundManager(), this, "sound.mp3");
 } catch (IOException e)
 {
 e.printStackTrace();
 } // Load our "music.mp3" file into a music objecttry {
 Music mMusic = MusicFactory.createMusicFromAsset(getMusicManager(), this, "music.mp3");
 } catch (IOException e)
 {
 e.printStackTrace();
 }

 Step3: After the sounds have been loaded into the SoundManager, we can play them as we see fit,
  by calling play() on them. be it during a collision, buttonclick or otherwise.

 //play the sound
 mSound.play()

 Step 4:
 The music objects should be handled in a different manner to sound objects,
 in cases where our music object should loop, continuously through the game, which is the most cases,
 we handle  play() and pause() methods, within the gameActivity lifecycle,

 /* Music objects which loop continuously should be played in* onResumeGame() of the activity life cycle
@Override
public synchronized void onResumeGame()
        {
        if(mMusic != null && !mMusic.isPlaying())
        { mMusic.play(); } super.onResumeGame();
        } Music objects which loop continuously should be paused in* onPauseGame() of the activity life cycle
@Override
public synchronized void onPauseGame()
        {
            if(mMusic != null && mMusic.isPlaying())
            {
                mMusic.pause();
            }
            super.onPauseGame();
        }



 *
 *
 */
public class ExampleSounds {
}
