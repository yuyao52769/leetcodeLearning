package cn.yuyao.hw.day0324;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.function.Function;

/**
 * 现场编程题题目内容：
 * 题目描述
 * 容器化是当前云化趋势下的一种重要技术，容器运行需要足够的CPU资源，请实现一种CPU分配机制，满足如下设计要求：
 * 假设所有虚拟机的 CPU核数都为 cpuCore 。
 * 为了满足可靠性要求，每个虚拟机上最多部署 2 个容器；一个容器占用一定数量的 CPU 核数，一个虚拟机上容器占用的CPU核数总和不能超过 cpuCore 。
 * 现有 A、B 两个业务，每个业务都有一个或多个微服务，每个微服务独占一个容器：
 *
 * 承载业务A 的每个容器需要的CPU核数记录于 serviceA 中，serviceA.length 为容器数量，serviceA[i] 表示容器 i 所需的CPU核数。业务B 的信息 serviceB 含义同理。
 * 业务A 需要支持反亲和策略，即业务A 的任意两个容器不能运行在同一个虚拟机上；业务B 不需要反亲和。
 * 请计算最少需要多少个虚拟机才能满足这两个业务的资源要求？
 *
 * 解答要求
 * 时间限制：800ms, 内存限制：256MB
 * 输入
 * 首行三个整数cpuCore serviceA.length serviceB.length
 * 第二行是 serviceA
 * 第三行是 serviceB
 *
 * 2 <= cpuCore <= 1000
 * 1 <= serviceA.length, serviceB.length <= 10^5, 1 <= serviceA[i], serviceB[i] <= cpuCore
 *
 * 输出
 * 一个整数，表示最少需要多少个虚拟机
 *
 * 样例
 * 输入样例 1
 *
 * 32 3 2
 * 16 8 16
 * 2 7
 * 输出样例 1
 *
 * 3
 * 提示样例 1
 * 每个虚拟机的CPU核数固定为 32， 业务A 的 3 个容器的CPU核数需求为 16、8、16，业务B 的 2 个容器的CPU核数需求为 2、7 。
 *
 * 由于A业务的反亲和要求，需要虚拟机的数量至少和A业务容器数相同，即 3 个；其中一种利用 3 个虚拟机满足CPU资源需求的分配方案为：
 * 虚拟机1：(A:16，B:2)
 * 虚拟机2：(A:8，B:7)
 * 虚拟机3：(A:16)
 *
 * 注意：每个虚拟机最多部署2个容器
 *
 *
 *
 * 输入样例 2
 *
 * 64 3 5
 * 32 8 16
 * 32 16 54 16 16
 * 输出样例 2
 *
 * 4
 * 提示样例 2
 * 最少需要 4 个虚拟机。可以有多个分配方案，其中之一：
 * 虚拟机1：(A:32，B:32)
 * 虚拟机2：(A:8，B:54)
 * 虚拟机3：(A:16，B:16)
 * 虚拟机4：(B:16，B:16)
 */
class Solution {
    // 待实现函数，在此函数中填入答题代码
    int getLeastVmNum(int cpuCore, int[] serviceA, int[] serviceB) {
        int[] vm = new int[serviceA.length + serviceB.length];
        for (int i = 0; i < serviceA.length; i++) {
            vm[i] = serviceA[i];
        }



        for (int i = 0; i < serviceA.length; i++) {



        }


        return -1;
    }
}

// 以下为试题输入输出框架，此部分代码非必要不建议改动
public class Main {
    // main入口由OJ平台调用
    public static void main(String[] args) throws IOException {
        try (InputReader reader = new InputReader()) {
            // 数据读入和题干输入栏描述一一对应，即一个输入一行代码读入，读者可不需要了解InputReader具体实现
            int[] paras = reader.readStrList(-1, "\\s+").stream().mapToInt(Integer::parseInt).toArray();
            int[] serviceA = reader.readStrList(paras[1], "\\s+").stream().mapToInt(Integer::parseInt).toArray();
            int[] serviceB = reader.readStrList(paras[2], "\\s+").stream().mapToInt(Integer::parseInt).toArray();

            int result = new Solution().getLeastVmNum(paras[0], serviceA, serviceB);
            System.out.println(result);
        }
    }
}

// 保证release的输入用例的格式正确，框架不做过度检查；如果本地联调输入不合法，直接抛异常，根据捕获异常进行定位处理；
class InputReader implements AutoCloseable {
    BufferedReader br = null;
    InputReader() {
        this.br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    }

    // 不单独提供readInt，使用如 Integer.parseInt(reader.readStr().trim()) 。另，不直接trim()，由使用者处理
    String readStr() throws IOException {
        return this.br.readLine();
    }

    // 数据格式：一种是单行多数据(num == -1)；一种是两行，首行是长度（可能为 0），次行是数据。
    // 仅原始类型使用[]形式，使用如 reader.readStrList(...).stream().mapToInt(Integer::parseInt).toArray();
    List<String> readStrList(int num, String delimiter) throws IOException {
        if (num == 0) {
            return new ArrayList<String>();
        }
        List<String> list = Arrays.stream(this.readStr().trim().split(delimiter))
                .collect(Collectors.toList());
        if (num > 0 && num != list.size()) {
            return new ArrayList<String>();
        }
        return list;
    }

    // 支持: 自定义List<FunctionApi>，List<String[]>，List<String>等
    // 使用如： List<String[]> list = reader.readObjListFromNline(rows, (str) -> str.split("\\s+"));
    <R> List<R> readObjListFromNline(int rows, Function<String, ? extends R> fromStr) throws IOException {
        if (rows <= 0) {
            return new ArrayList<R>();
        }
        List<R> list = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            list.add(fromStr.apply(this.readStr()));
        }
        return list;
    }

    @Override // try-resource自动释放资源
    public void close() throws IOException {
        this.br.close();
    }
}
