package jp.makizakao.type;

import arc.struct.Seq;
import multicraft.IOEntry;

public class ResultEntry extends IOEntry {
    public Seq<ResultStack> items = new Seq<>();
    public ResultEntry() {}
}
