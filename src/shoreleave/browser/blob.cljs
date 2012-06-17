(ns shoreleave.browser.blob
  "An idiomatic interface to Blobs")

;; Blobs
;; -----
;;
;; HTML5 File API supports the creation of Blobs.
;;
;; Blobs allow you to take arbitrary text (like functions) and create file-like
;; objects, that get their own unique URL wuth a `blob://...` schema.
;;
;; This is useful if you're making an app that wants to use on-demand assets,
;; or you need to build something like embedded web workers.
;;
;; *THESE HTML5 API SPECS ARE STILL CHANGING*
;;
;; The BlobBuilder API has been deprecated in favor of Blob objects, but there
;; could be backwards support.  Until the APIs settle, this will do.
;;
;; The Blobber object (`js/window.BlobBuilder`) supports the following protocol calls:
;;
;;  * `(conj! ...)` - add more text to the blob-in-progress

(defn- blobber
  "Define a `window` level property for BlobBuilder, that removes Browser
  specific names"
  ([]
   (blobber js/window))
  ([w]
   (or (.-BlobBuilder w) (.-WebKitBlobBuilder w) (.-MozBlobBuilder w))))

;; Make sure we have a top-level BlobBuiler - this is for protocol sake
(set! (.-BlobBuilder js/window) (blobber))

(defn blob-builder
  "Return a BlobBuilder JavaScript object"
  []
  (js/window.BlobBuilder.))


(extend-type js/window.BlobBuilder
  
  ITransientCollection
  ;(-persistent! [blobber] (as-vector blobber))
  (-conj! [blobber str-piece]
    (.append blobber str-piece)))

(defn blob
  "Build the blobber's contents into a Blob and return it"
  [blobber]
  (.getBlob blobber))

(defn object-url!
  "Create a unique object URL (ala `blob://...`) for a Blob object,
  as returned from `(blob ...)`"
  [file-or-blob]
  (let [url (or (.-URL js/window) (.-webkitURL js/window))]
    (.createObjectURL url file-or-blob)))

