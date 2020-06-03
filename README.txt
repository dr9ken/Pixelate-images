->RU<-
Пакет классов, пикселизирующий изображение

Применение:
1)Создание объекта класса Pixelation. В конструкторе указывается:
	-сторона одного пикселя, являющаяся степенью двойки (например, в пиксель со стороной два будут включены 		2x2=4 пикселя)
	-путь к изображению (желательно jpg) размером кратному степени двойки(64x64, 32x128, 512x1024)
2)Новое изображание сохраняется в папку с проектом под названием "pixelImage.jpg"

->ENG<-
A class package that pixelates an image

Application:
1)Creating an object of the Pixelation class. The constructor indicates:
	-side of one pixel, which is a power of two (for example, 2x2 = 4 pixels will be included in a pixel with 	side two)
	-path to the image (preferably jpg) multiple of the power of two (64x64, 32x128, 512x1024)
2)The new image is saved in the project folder called "pixelImage.jpg"