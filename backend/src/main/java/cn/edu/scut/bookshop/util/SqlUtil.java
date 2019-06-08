package cn.edu.scut.bookshop.util;

import cn.edu.scut.bookshop.domain.ErrorCode;

import java.util.Set;

public class SqlUtil
{
    public static void checkFavoriteType(String favorite_type)
    {
        if(!"author".equals(favorite_type) && !"book".equals(favorite_type))
            throw new BusinessException(ErrorCode.PARAM_ERR_FAVORITE_TYPE);
    }
    
    /**
     * 构造mysql in子句
     * where id in (0, 5, 9)
     * @param fieldSet
     * @return
     */
    public static String in_set_str(Set fieldSet)
    {
        StringBuilder sb = new StringBuilder("(");
        for(Object item: fieldSet)
            sb.append(item.toString()).append(",");
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }
}
