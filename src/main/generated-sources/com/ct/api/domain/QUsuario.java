package com.ct.api.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUsuario is a Querydsl query type for Usuario
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUsuario extends EntityPathBase<Usuario> {

    private static final long serialVersionUID = -1552188888L;

    public static final QUsuario usuario = new QUsuario("usuario");

    public final BooleanPath ativo = createBoolean("ativo");

    public final StringPath celular = createString("celular");

    public final StringPath cidade = createString("cidade");

    public final DateTimePath<java.util.Date> dataAtualizacao = createDateTime("dataAtualizacao", java.util.Date.class);

    public final DateTimePath<java.util.Date> dataCadastro = createDateTime("dataCadastro", java.util.Date.class);

    public final DatePath<java.util.Date> dataExclusao = createDate("dataExclusao", java.util.Date.class);

    public final StringPath email = createString("email");

    public final StringPath foto = createString("foto");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nomeUsuario = createString("nomeUsuario");

    public final NumberPath<Long> plano = createNumber("plano", Long.class);

    public final StringPath senha = createString("senha");

    public final StringPath uf = createString("uf");

    public QUsuario(String variable) {
        super(Usuario.class, forVariable(variable));
    }

    public QUsuario(Path<? extends Usuario> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUsuario(PathMetadata metadata) {
        super(Usuario.class, metadata);
    }

}

