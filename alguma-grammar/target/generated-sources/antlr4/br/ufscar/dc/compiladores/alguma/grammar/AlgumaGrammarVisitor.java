// Generated from java-escape by ANTLR 4.11.1
package br.ufscar.dc.compiladores.alguma.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AlgumaGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AlgumaGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(AlgumaGrammarParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#op_logico_1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_logico_1(AlgumaGrammarParser.Op_logico_1Context ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#op_logico_2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_logico_2(AlgumaGrammarParser.Op_logico_2Context ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#declaracoes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracoes(AlgumaGrammarParser.DeclaracoesContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#tipo_basico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_basico(AlgumaGrammarParser.Tipo_basicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#identificador}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentificador(AlgumaGrammarParser.IdentificadorContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#variavel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariavel(AlgumaGrammarParser.VariavelContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#decl_local_global}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl_local_global(AlgumaGrammarParser.Decl_local_globalContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#tipo_basico_ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_basico_ident(AlgumaGrammarParser.Tipo_basico_identContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#declaracao_local}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracao_local(AlgumaGrammarParser.Declaracao_localContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#dimensao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensao(AlgumaGrammarParser.DimensaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(AlgumaGrammarParser.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#tipo_estendido}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_estendido(AlgumaGrammarParser.Tipo_estendidoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#valor_constante}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValor_constante(AlgumaGrammarParser.Valor_constanteContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#registro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegistro(AlgumaGrammarParser.RegistroContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#declaracao_global}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracao_global(AlgumaGrammarParser.Declaracao_globalContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametro(AlgumaGrammarParser.ParametroContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#parametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametros(AlgumaGrammarParser.ParametrosContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#corpo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCorpo(AlgumaGrammarParser.CorpoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmd(AlgumaGrammarParser.CmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#cmdLeia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdLeia(AlgumaGrammarParser.CmdLeiaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#cmdEscreva}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdEscreva(AlgumaGrammarParser.CmdEscrevaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#cmdSe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdSe(AlgumaGrammarParser.CmdSeContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#cmdCaso}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdCaso(AlgumaGrammarParser.CmdCasoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#cmdPara}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdPara(AlgumaGrammarParser.CmdParaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdEnquanto(AlgumaGrammarParser.CmdEnquantoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#cmdFaca}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdFaca(AlgumaGrammarParser.CmdFacaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#cmdAtribuicao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdAtribuicao(AlgumaGrammarParser.CmdAtribuicaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#cmdChamada}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdChamada(AlgumaGrammarParser.CmdChamadaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#cmdRetorne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdRetorne(AlgumaGrammarParser.CmdRetorneContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#selecao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelecao(AlgumaGrammarParser.SelecaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#item_selecao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitItem_selecao(AlgumaGrammarParser.Item_selecaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#constantes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantes(AlgumaGrammarParser.ConstantesContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#numero_intervalo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumero_intervalo(AlgumaGrammarParser.Numero_intervaloContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#op_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_unario(AlgumaGrammarParser.Op_unarioContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#exp_aritmetica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_aritmetica(AlgumaGrammarParser.Exp_aritmeticaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#termo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermo(AlgumaGrammarParser.TermoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#fator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFator(AlgumaGrammarParser.FatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#op1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp1(AlgumaGrammarParser.Op1Context ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#op2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp2(AlgumaGrammarParser.Op2Context ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#op3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp3(AlgumaGrammarParser.Op3Context ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#parcela}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela(AlgumaGrammarParser.ParcelaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#parcela_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_unario(AlgumaGrammarParser.Parcela_unarioContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#parcela_nao_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_nao_unario(AlgumaGrammarParser.Parcela_nao_unarioContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#exp_relacional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_relacional(AlgumaGrammarParser.Exp_relacionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#op_relacional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_relacional(AlgumaGrammarParser.Op_relacionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressao(AlgumaGrammarParser.ExpressaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#termo_logico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermo_logico(AlgumaGrammarParser.Termo_logicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#fator_logico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFator_logico(AlgumaGrammarParser.Fator_logicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgumaGrammarParser#parcela_logica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_logica(AlgumaGrammarParser.Parcela_logicaContext ctx);
}