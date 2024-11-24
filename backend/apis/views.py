from django.http import JsonResponse
from rest_framework import status
from rest_framework.decorators import api_view
from LibLlie.troditionAlgorithm.utils import script_ta

from .utils import *


@api_view(['POST'])
def handle(request):
    result = {
        'code': 0,
        'data': None,
        'message': None,
    }
    img_format, img_base64 = None, None

    try:
        # 读取图片
        filebytes, img_format = image_to_bytes(request.FILES['file'])

        # 处理图片
        processed_img = script_ta(
            img_path=filebytes,
            algorithm=request.data['method'],
            color_space=request.data['cs'],
            showimg=False,
            saveimg=False,
            format=img_format,
        )
        print('image has been processed')

        # 把图片压缩并转成base64返回
        img_base64 = ndarray_to_base64(processed_img, img_format)

    except Exception as e:
        print('{} : {}'.format(type(e), e))
        result['message'] = str(e)
        result['code'] = 0
        return JsonResponse(result, status=status.HTTP_500_INTERNAL_SERVER_ERROR)

    finally:
        result['code'] = 1
        result['data'] = f'data:image/{img_format};base64,{img_base64}'
        result['message'] = 'success'
        return JsonResponse(result, status=status.HTTP_200_OK)
