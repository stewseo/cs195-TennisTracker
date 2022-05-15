package com.example.cs195tennis.controller.TableColumnListeners;

import com.example.cs195tennis.Dao.DataModel.TournamentRecord;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.factories.MFXAnimationFactory;
import org.jooq.ExecuteType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ColumnListener {
    MFXAnimationFactory mfxAnimationFactory;
    MFXTableColumn<TournamentRecord>[] mfxTableColumns;
    String[] fields;

    public ColumnListener() {}
}
