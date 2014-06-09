CKEDITOR.editorConfig = function( config )
{
// 에디터 사용언어, UI 배경색 설정
config.language = 'en';
config.uiColor = '#DEDBDB';
// 파일브라우저, 즉, 파일 업로드 기능을 사용을 위한 설정(파일, 이미지, 플래시)
config.filebrowserBrowseUrl = '/ckfinder/ckfinder.html';
config.filebrowserImageBrowseUrl = '/ckfinder/ckfinder.html?type=Image';
config.filebrowserFlashBrowseUrl = '/ckfinder/ckfinder.html?type=Flash';
        // 파일브라우저, 즉, 파일 업로드 기능을 사용을 위한 자바 모듈 정의(파일, 이미지, 플래시)
config.filebrowserUploadUrl = '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files';
config.filebrowserImageUploadUrl	= '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images';
    config.filebrowserFlashUploadUrl = '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';
// 사용자 도구막대 설정이름
config.toolbar = 'MyToolbar';
 // 사용자 도구막대 설정
config.toolbar_MyToolbar =
[
{ name: 'document', items : [ 'NewPage','Preview' ] },
{ name: 'clipboard', items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] },
{ name: 'editing', items : [ 'Find','Replace','-','SelectAll','-','Scayt' ] },
{ name: 'insert', items : [ 'Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak','Iframe' ] },
                '/', 
{ name: 'styles', items : [ 'Styles','Format' ] },
{ name: 'basicstyles', items : [ 'Bold','Italic','Strike','-','RemoveFormat' ] },
{ name: 'paragraph', items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote' ] },
{ name: 'links', items : [ 'Link','Unlink','Anchor' ] },
{ name: 'tools', items : [ 'Maximize','-','About' ] }
];
};