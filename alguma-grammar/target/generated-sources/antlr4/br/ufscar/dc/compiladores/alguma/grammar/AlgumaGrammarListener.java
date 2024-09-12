// Generated from java-escape by ANTLR 4.11.1
package br.ufscar.dc.compiladores.alguma.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AlgumaGrammarParser}.
 */
public interface AlgumaGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(AlgumaGrammarParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(AlgumaGrammarParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#op_logico_1}.
	 * @param ctx the parse tree
	 */
	void enterOp_logico_1(AlgumaGrammarParser.Op_logico_1Context ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#op_logico_1}.
	 * @param ctx the parse tree
	 */
	void exitOp_logico_1(AlgumaGrammarParser.Op_logico_1Context ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#op_logico_2}.
	 * @param ctx the parse tree
	 */
	void enterOp_logico_2(AlgumaGrammarParser.Op_logico_2Context ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#op_logico_2}.
	 * @param ctx the parse tree
	 */
	void exitOp_logico_2(AlgumaGrammarParser.Op_logico_2Context ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#declaracoes}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracoes(AlgumaGrammarParser.DeclaracoesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#declaracoes}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracoes(AlgumaGrammarParser.DeclaracoesContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#tipo_basico}.
	 * @param ctx the parse tree
	 */
	void enterTipo_basico(AlgumaGrammarParser.Tipo_basicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#tipo_basico}.
	 * @param ctx the parse tree
	 */
	void exitTipo_basico(AlgumaGrammarParser.Tipo_basicoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#identificador}.
	 * @param ctx the parse tree
	 */
	void enterIdentificador(AlgumaGrammarParser.IdentificadorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#identificador}.
	 * @param ctx the parse tree
	 */
	void exitIdentificador(AlgumaGrammarParser.IdentificadorContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#variavel}.
	 * @param ctx the parse tree
	 */
	void enterVariavel(AlgumaGrammarParser.VariavelContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#variavel}.
	 * @param ctx the parse tree
	 */
	void exitVariavel(AlgumaGrammarParser.VariavelContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#decl_local_global}.
	 * @param ctx the parse tree
	 */
	void enterDecl_local_global(AlgumaGrammarParser.Decl_local_globalContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#decl_local_global}.
	 * @param ctx the parse tree
	 */
	void exitDecl_local_global(AlgumaGrammarParser.Decl_local_globalContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#tipo_basico_ident}.
	 * @param ctx the parse tree
	 */
	void enterTipo_basico_ident(AlgumaGrammarParser.Tipo_basico_identContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#tipo_basico_ident}.
	 * @param ctx the parse tree
	 */
	void exitTipo_basico_ident(AlgumaGrammarParser.Tipo_basico_identContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#declaracao_local}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracao_local(AlgumaGrammarParser.Declaracao_localContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#declaracao_local}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracao_local(AlgumaGrammarParser.Declaracao_localContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#dimensao}.
	 * @param ctx the parse tree
	 */
	void enterDimensao(AlgumaGrammarParser.DimensaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#dimensao}.
	 * @param ctx the parse tree
	 */
	void exitDimensao(AlgumaGrammarParser.DimensaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(AlgumaGrammarParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(AlgumaGrammarParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#tipo_estendido}.
	 * @param ctx the parse tree
	 */
	void enterTipo_estendido(AlgumaGrammarParser.Tipo_estendidoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#tipo_estendido}.
	 * @param ctx the parse tree
	 */
	void exitTipo_estendido(AlgumaGrammarParser.Tipo_estendidoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#valor_constante}.
	 * @param ctx the parse tree
	 */
	void enterValor_constante(AlgumaGrammarParser.Valor_constanteContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#valor_constante}.
	 * @param ctx the parse tree
	 */
	void exitValor_constante(AlgumaGrammarParser.Valor_constanteContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#registro}.
	 * @param ctx the parse tree
	 */
	void enterRegistro(AlgumaGrammarParser.RegistroContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#registro}.
	 * @param ctx the parse tree
	 */
	void exitRegistro(AlgumaGrammarParser.RegistroContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#declaracao_global}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracao_global(AlgumaGrammarParser.Declaracao_globalContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#declaracao_global}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracao_global(AlgumaGrammarParser.Declaracao_globalContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParametro(AlgumaGrammarParser.ParametroContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParametro(AlgumaGrammarParser.ParametroContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#parametros}.
	 * @param ctx the parse tree
	 */
	void enterParametros(AlgumaGrammarParser.ParametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#parametros}.
	 * @param ctx the parse tree
	 */
	void exitParametros(AlgumaGrammarParser.ParametrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#corpo}.
	 * @param ctx the parse tree
	 */
	void enterCorpo(AlgumaGrammarParser.CorpoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#corpo}.
	 * @param ctx the parse tree
	 */
	void exitCorpo(AlgumaGrammarParser.CorpoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(AlgumaGrammarParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(AlgumaGrammarParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#cmdLeia}.
	 * @param ctx the parse tree
	 */
	void enterCmdLeia(AlgumaGrammarParser.CmdLeiaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#cmdLeia}.
	 * @param ctx the parse tree
	 */
	void exitCmdLeia(AlgumaGrammarParser.CmdLeiaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#cmdEscreva}.
	 * @param ctx the parse tree
	 */
	void enterCmdEscreva(AlgumaGrammarParser.CmdEscrevaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#cmdEscreva}.
	 * @param ctx the parse tree
	 */
	void exitCmdEscreva(AlgumaGrammarParser.CmdEscrevaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#cmdSe}.
	 * @param ctx the parse tree
	 */
	void enterCmdSe(AlgumaGrammarParser.CmdSeContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#cmdSe}.
	 * @param ctx the parse tree
	 */
	void exitCmdSe(AlgumaGrammarParser.CmdSeContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#cmdCaso}.
	 * @param ctx the parse tree
	 */
	void enterCmdCaso(AlgumaGrammarParser.CmdCasoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#cmdCaso}.
	 * @param ctx the parse tree
	 */
	void exitCmdCaso(AlgumaGrammarParser.CmdCasoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#cmdPara}.
	 * @param ctx the parse tree
	 */
	void enterCmdPara(AlgumaGrammarParser.CmdParaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#cmdPara}.
	 * @param ctx the parse tree
	 */
	void exitCmdPara(AlgumaGrammarParser.CmdParaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 */
	void enterCmdEnquanto(AlgumaGrammarParser.CmdEnquantoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 */
	void exitCmdEnquanto(AlgumaGrammarParser.CmdEnquantoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#cmdFaca}.
	 * @param ctx the parse tree
	 */
	void enterCmdFaca(AlgumaGrammarParser.CmdFacaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#cmdFaca}.
	 * @param ctx the parse tree
	 */
	void exitCmdFaca(AlgumaGrammarParser.CmdFacaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#cmdAtribuicao}.
	 * @param ctx the parse tree
	 */
	void enterCmdAtribuicao(AlgumaGrammarParser.CmdAtribuicaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#cmdAtribuicao}.
	 * @param ctx the parse tree
	 */
	void exitCmdAtribuicao(AlgumaGrammarParser.CmdAtribuicaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#cmdChamada}.
	 * @param ctx the parse tree
	 */
	void enterCmdChamada(AlgumaGrammarParser.CmdChamadaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#cmdChamada}.
	 * @param ctx the parse tree
	 */
	void exitCmdChamada(AlgumaGrammarParser.CmdChamadaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#cmdRetorne}.
	 * @param ctx the parse tree
	 */
	void enterCmdRetorne(AlgumaGrammarParser.CmdRetorneContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#cmdRetorne}.
	 * @param ctx the parse tree
	 */
	void exitCmdRetorne(AlgumaGrammarParser.CmdRetorneContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#selecao}.
	 * @param ctx the parse tree
	 */
	void enterSelecao(AlgumaGrammarParser.SelecaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#selecao}.
	 * @param ctx the parse tree
	 */
	void exitSelecao(AlgumaGrammarParser.SelecaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#item_selecao}.
	 * @param ctx the parse tree
	 */
	void enterItem_selecao(AlgumaGrammarParser.Item_selecaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#item_selecao}.
	 * @param ctx the parse tree
	 */
	void exitItem_selecao(AlgumaGrammarParser.Item_selecaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#constantes}.
	 * @param ctx the parse tree
	 */
	void enterConstantes(AlgumaGrammarParser.ConstantesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#constantes}.
	 * @param ctx the parse tree
	 */
	void exitConstantes(AlgumaGrammarParser.ConstantesContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#numero_intervalo}.
	 * @param ctx the parse tree
	 */
	void enterNumero_intervalo(AlgumaGrammarParser.Numero_intervaloContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#numero_intervalo}.
	 * @param ctx the parse tree
	 */
	void exitNumero_intervalo(AlgumaGrammarParser.Numero_intervaloContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#op_unario}.
	 * @param ctx the parse tree
	 */
	void enterOp_unario(AlgumaGrammarParser.Op_unarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#op_unario}.
	 * @param ctx the parse tree
	 */
	void exitOp_unario(AlgumaGrammarParser.Op_unarioContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#exp_aritmetica}.
	 * @param ctx the parse tree
	 */
	void enterExp_aritmetica(AlgumaGrammarParser.Exp_aritmeticaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#exp_aritmetica}.
	 * @param ctx the parse tree
	 */
	void exitExp_aritmetica(AlgumaGrammarParser.Exp_aritmeticaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(AlgumaGrammarParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(AlgumaGrammarParser.TermoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#fator}.
	 * @param ctx the parse tree
	 */
	void enterFator(AlgumaGrammarParser.FatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#fator}.
	 * @param ctx the parse tree
	 */
	void exitFator(AlgumaGrammarParser.FatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#op1}.
	 * @param ctx the parse tree
	 */
	void enterOp1(AlgumaGrammarParser.Op1Context ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#op1}.
	 * @param ctx the parse tree
	 */
	void exitOp1(AlgumaGrammarParser.Op1Context ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#op2}.
	 * @param ctx the parse tree
	 */
	void enterOp2(AlgumaGrammarParser.Op2Context ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#op2}.
	 * @param ctx the parse tree
	 */
	void exitOp2(AlgumaGrammarParser.Op2Context ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#op3}.
	 * @param ctx the parse tree
	 */
	void enterOp3(AlgumaGrammarParser.Op3Context ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#op3}.
	 * @param ctx the parse tree
	 */
	void exitOp3(AlgumaGrammarParser.Op3Context ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#parcela}.
	 * @param ctx the parse tree
	 */
	void enterParcela(AlgumaGrammarParser.ParcelaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#parcela}.
	 * @param ctx the parse tree
	 */
	void exitParcela(AlgumaGrammarParser.ParcelaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#parcela_unario}.
	 * @param ctx the parse tree
	 */
	void enterParcela_unario(AlgumaGrammarParser.Parcela_unarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#parcela_unario}.
	 * @param ctx the parse tree
	 */
	void exitParcela_unario(AlgumaGrammarParser.Parcela_unarioContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#parcela_nao_unario}.
	 * @param ctx the parse tree
	 */
	void enterParcela_nao_unario(AlgumaGrammarParser.Parcela_nao_unarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#parcela_nao_unario}.
	 * @param ctx the parse tree
	 */
	void exitParcela_nao_unario(AlgumaGrammarParser.Parcela_nao_unarioContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#exp_relacional}.
	 * @param ctx the parse tree
	 */
	void enterExp_relacional(AlgumaGrammarParser.Exp_relacionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#exp_relacional}.
	 * @param ctx the parse tree
	 */
	void exitExp_relacional(AlgumaGrammarParser.Exp_relacionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#op_relacional}.
	 * @param ctx the parse tree
	 */
	void enterOp_relacional(AlgumaGrammarParser.Op_relacionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#op_relacional}.
	 * @param ctx the parse tree
	 */
	void exitOp_relacional(AlgumaGrammarParser.Op_relacionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#expressao}.
	 * @param ctx the parse tree
	 */
	void enterExpressao(AlgumaGrammarParser.ExpressaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#expressao}.
	 * @param ctx the parse tree
	 */
	void exitExpressao(AlgumaGrammarParser.ExpressaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#termo_logico}.
	 * @param ctx the parse tree
	 */
	void enterTermo_logico(AlgumaGrammarParser.Termo_logicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#termo_logico}.
	 * @param ctx the parse tree
	 */
	void exitTermo_logico(AlgumaGrammarParser.Termo_logicoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#fator_logico}.
	 * @param ctx the parse tree
	 */
	void enterFator_logico(AlgumaGrammarParser.Fator_logicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#fator_logico}.
	 * @param ctx the parse tree
	 */
	void exitFator_logico(AlgumaGrammarParser.Fator_logicoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgumaGrammarParser#parcela_logica}.
	 * @param ctx the parse tree
	 */
	void enterParcela_logica(AlgumaGrammarParser.Parcela_logicaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgumaGrammarParser#parcela_logica}.
	 * @param ctx the parse tree
	 */
	void exitParcela_logica(AlgumaGrammarParser.Parcela_logicaContext ctx);
}