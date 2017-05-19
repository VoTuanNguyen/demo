<%@tag description="Tag Edit" pageEncoding="UTF-8"%>
<html>
<body>
	<script src="ckeditor/ckeditor.js"></script>
	<td>
		<form action="" method="post">
			<!-- (2): textarea sẽ được thay thế bởi CKEditor -->
			<textarea id="editor1" name="editor1" cols="80" rows="10">
			</p>
       </textarea>
			<!-- (3): Code Javascript thay thế textarea có id='editor1' bởi CKEditor -->
			<script>
				CKEDITOR.replace('editor1');
			</script>
		</form>
	<td>
</body>
</html>
