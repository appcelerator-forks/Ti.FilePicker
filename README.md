#Ti.FilePicker

This module written by Jorge <img src="https://avatars.slack-edge.com/2016-07-26/63153065088_2171bf30e75d6d921ae9_192.jpg" width=80 /> and Rainer <img src="https://avatars.slack-edge.com/2016-09-11/78462010647_0c72b9186ef4032b7ed7_512.png" width=80>   is an Titanium Android module for picking files from device.


##Interface


```javascript
var FilePicker = require("ti.filepicker");
FilePicker.getFileSelectDialog({
  mimeTypes: ["*/pdf"],
  resultType : FilePicker.TYPE_FILE, 
  onSuccess : function(_e) {
    var resultFile = _e.file;
  }  
});

```
Or:
```javascript
var FilePicker = require("ti.filepicker");
FilePicker.getFileSelectDialog({
    mimeTypes: ["*/pdf"],
    resultType : FilePicker.TYPE_BLOB
    onSuccess : function(_e) {
        var resultBlob = _e.blob;
    }
});
```

<img src="http://i.imgur.com/rvY4vrr.png" width=300 />
<img src="http://i.imgur.com/ShCq3NW.png" width=300 />
