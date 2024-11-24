import base64
from io import BytesIO

from PIL import Image


def ndarray_to_base64(image_array, img_format):
    image_array[:, :, [0, 2]] = image_array[:, :, [2, 0]]
    # 将 ndarray 转换为 PIL 图像
    image = Image.fromarray(image_array)
    # 将 PIL 图像保存到 BytesIO 对象中
    buffer = BytesIO()
    image.save(buffer, format=img_format)

    # 获取二进制数据
    image_binary = buffer.getvalue()
    # 压缩图片
    while len(image_binary) // (1024 * 1024) > 4:
        # 计算新的尺寸
        new_size = [x // 2 for x in image.size]
        image = image.resize((new_size[0], new_size[1]))

        # 重新保存图片到 BytesIO 对象中
        buffer = BytesIO()
        image.save(buffer, format=img_format)
        image_binary = buffer.getvalue()
        print(f'image has been resized to {len(image_binary) / (1024 * 1024)}MB')

    # 转换为 base64 编码的字符串
    image_base64 = base64.b64encode(image_binary).decode('utf-8')

    return image_base64


def image_to_bytes(file):
    img_format = file.name.split('.')[-1]
    img_format = 'jpeg' if img_format == 'jpg' else img_format
    file.seek(0)
    filebytes = file.read()

    return filebytes, img_format
