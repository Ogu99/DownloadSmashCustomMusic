Smash Custom Music Downloader
-

A application originally by RedStoneMatt written in <b>Java</b>. It aims to download music from http://www.smashcustommusic.com/ (link broken, site down) through the application by accessing the web archive https://archive.org/web/.
<p>
Heavily modified and recoded by me. Tried to make the application more accessable for users through a running command line interface. Also tried to make the program more accessable for programers. I made this fork as a little fun project.

How to use it?
-

Unlike the original application which is run through the arguments when executing the file, this version uses a command line interface. So you can download the source code (only 2 classes) paste it in whatever IDE you prefer and start that program. You will then encounter the running interface which will wait to read your commands.
<p>
When starting the application you have the option to use <b>two</b> commands: quit and the download command.
<p>
Here are the syntaxes:
<ul>
  <li> <i>quit</i>: Immediately ends the program </li>
  <li> <i> <b>startId</b> <b>endId</b> <b>[brstm, bcstm, bfstm, sw_bfstm]</b></i>: This command actually downloads the requested files. Every argument is seperated by a whitespace. You first HAVE to enter to id numbers, the startid (inclusive) and the endid(inclusive) for the song. Then you can add between one and four argument (the music file types) which have to be written exactly like above. Finally you enter your destination folder and then the program should download your files. </li>
</ul>
  
Credits
-

(c) 2020 Ogu99
(c) 2020 originally by RedStoneMatt
