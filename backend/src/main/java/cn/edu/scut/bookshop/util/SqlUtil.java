package cn.edu.scut.bookshop.util;

import java.util.Set;

public class SqlUtil
{
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
