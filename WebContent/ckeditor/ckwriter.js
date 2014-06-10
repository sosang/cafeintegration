/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.language = 'ko';

// Remove some buttons, provided by the standard plugins, which we don't
	// need to have in the Standard(s) toolbar.
	 config.removeButtons = 'Underline,Subscript,Superscript';
	    config.filebrowserBrowseUrl = '/cafeintegration/ckfinder/ckfinder.html';
	    config.filebrowserImageBrowseUrl = '/cafeintegration/ckfinder/ckfinder.html?type=Images';
	    config.filebrowserFlashBrowseUrl = '/cafeintegration/ckfinder/ckfinder.html?type=Flash';
	    config.filebrowserUploadUrl = '/cafeintegration/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files';
	    config.filebrowserImageUploadUrl = '/cafeintegration/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images';
	    config.filebrowserFlashUploadUrl = '/cafeintegration/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';
	    //font config
	    config.font_defaultLabel = '굴림';
	    config.font_names = '굴림/굴림;Apple SD 산돌고딕 Neo/Apple SD 산돌고딕 Neo;나눔고딕/나눔고딕;나눔명조/나눔명조;Gungsuh/Gungsuh;Arial/Arial;Tahoma/Tahoma;Verdana/Verdana';
//config toolbar
  
 config.toolbar_WriteToolBar =
[
['Source'],
	['Cut','Copy','Paste','PasteText','PasteFromWord','-','SpellChecker', 'Scayt'],
	['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],				
	'/',
	['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
	['NumberedList','BulletedList','-','Outdent','Indent','Blockquote','CreateDiv'],
	['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],	
	['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar'],
	'/',
	['Styles','Format','Font','FontSize'],
	['TextColor','BGColor'],
	['Maximize','-','About']
];

config.toolbar = 'WriteToolBar';
};


