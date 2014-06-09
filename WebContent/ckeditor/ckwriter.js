/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.language = 'kr';

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

CKEDITOR.on( 'dialogDefinition', function( ev ) {
  var tab, field, dialogName = ev.data.name,
      dialogDefinition = ev.data.definition;
  var timerVal = null;
  var defaultImageSize = 550;

  var infoTab; 
  
  
  function timeoutHander()
	{
	  	//������ ���� info�̸� 
		var dialog = CKEDITOR.dialog.getCurrent();
		var img = new Image();
		//var img1 = new Image();
		var url = dialog.getValueOf('info','txtUrl');			
	  	img.src = url; // �̹��� ����
	  	//alert(img.src);
	  	if(img.width > 0 && img.height > 0)
	  	{
	  		var width = dialog.getValueOf('info','txtWidth');
	  		var height = dialog.getValueOf('info','txtHeight');
	  		clearInterval(timerVal);
	  			  		
	  		if(defaultImageSize > img.width)
	  			dialog.setValueOf('info','txtWidth',img.width);
	  		if(defaultImageSize <= img.width)
	  		{
	  		  var ratio = defaultImageSize/img.width;	  		
	  		  dialog.setValueOf('info','txtWidth',parseInt(img.width*ratio));
	  		  dialog.setValueOf('info','txtHeight',parseInt(img.height*ratio));
	  		}
	  		
	  		img = null;
	  	}
	  	else	  		
	  	img = null;
  	
	}

  if( dialogName == 'image' )
  { 

	
	infoTab = dialogDefinition.getContents( 'info' );
	txtWidth = infoTab.get( 'txtWidth' );
    txtWidth['default'] = defaultImageSize;
    //link tab remove
    dialogDefinition.removeContents('link');
    //advanced tab remove
    dialogDefinition.removeContents('advanced');
 
	
	dialogDefinition.onLoad = function()
	{
			
		 this.selectPage('Upload');
		
		var dialog = CKEDITOR.dialog.getCurrent();		
		
		dialog.on( 'selectPage', function( ev ) {
			
			//alert(ev.data.page);
			if(ev.data.page =='info')
			{				
	        	timerVal = setInterval(timeoutHander,100);        				       	
			}			
		});	
	}
  }  //if( dialogName == 'image' )
	
	
  
});

};


